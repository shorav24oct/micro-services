package com.movie.catalog.resource;

import java.util.Arrays;
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
import com.movie.catalog.model.Rating;
import com.movie.catalog.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClientBuilder;

	/*
	 * use small case for writing the application name MOVIE-INFO-SERVICE:
	 * movie-info-service
	 */
	private static final String MOVIE_INFO_URL = "http://movie-info-service/movieinfo/";
	private static final String RATING_INFO_URL = "http://ratings-data-service/ratingsdata/users/";

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		UserRating userRating = getUserRating(userId);

		return userRating.getListRatings().stream().map(rating -> getCatalogItem(rating)).collect(Collectors.toList());
	}

	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
	private CatalogItem getCatalogItem(Rating rating) {
		MovieInfo movieInfo = restTemplate.getForObject(MOVIE_INFO_URL + rating.getMovieId(), MovieInfo.class);

		return new CatalogItem(rating.getRating(), movieInfo.getName(), "Revenge of the Fallen");
	}
	
	private CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem(rating.getRating(), "Movie Name not found", "");
		
	}

	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	private UserRating getUserRating(String userId) {
		return restTemplate.getForObject(RATING_INFO_URL + userId, UserRating.class);
	}
	
	private UserRating getFallbackUserRating(String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setListRatings(Arrays.asList(
				new Rating(0, "")
				));
		return userRating;
	}

}

/*
 * MovieInfo movieInfo = webClientBuilder.build() .get() .uri(MOVIE_INFO_URL +
 * rating.getMovieId()) .retrieve() .bodyToMono(MovieInfo.class) .block();
 */