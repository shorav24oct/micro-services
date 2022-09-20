package com.movie.catalog.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.movie.catalog.model.CatalogItem;
import com.movie.catalog.model.UserRating;
import com.movie.catalog.service.MovieInfoService;
import com.movie.catalog.service.UserRatingService;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	public UserRatingService userRatingService;

	@Autowired
	public MovieInfoService movieInfoService;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		UserRating userRating = userRatingService.getUserRating(userId);

		return userRating.getListRatings().stream().map(rating -> movieInfoService.getCatalogItem(rating))
				.collect(Collectors.toList());
	}

}

/*
 * MovieInfo movieInfo = webClientBuilder.build() .get() .uri(MOVIE_INFO_URL +
 * rating.getMovieId()) .retrieve() .bodyToMono(MovieInfo.class) .block();
 */