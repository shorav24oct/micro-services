package com.movie.catalog.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.movie.catalog.model.CatalogItem;
import com.movie.catalog.model.MovieInfo;
import com.movie.catalog.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClientBuilder;

	private static final String MOVIE_INFO_URL = "http://localhost:8082/movieinfo/";
	private static final String RATING_INFO_URL = "http://localhost:8083/ratingsdata/users/";

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		UserRating userRating = restTemplate.getForObject(RATING_INFO_URL + userId, UserRating.class);

		return userRating.getListRatings().stream().map(rating -> {
			MovieInfo movieInfo = restTemplate.getForObject(MOVIE_INFO_URL + rating.getMovieId(), MovieInfo.class);

			return new CatalogItem(rating.getRating(), movieInfo.getName(), "Revenge of the Fallen");
		}).collect(Collectors.toList());
	}

}

/*
 * MovieInfo movieInfo = webClientBuilder.build() .get() .uri(MOVIE_INFO_URL +
 * rating.getMovieId()) .retrieve() .bodyToMono(MovieInfo.class) .block();
 */