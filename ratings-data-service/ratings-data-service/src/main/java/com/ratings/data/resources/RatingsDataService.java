package com.ratings.data.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratings.data.model.Rating;
import com.ratings.data.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataService {
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(4, movieId);
	}
	
	@RequestMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String movieId) {
		List<Rating> ratings = Arrays.asList(new Rating(4, "1234"), new Rating(3, "5678"));
		UserRating userRating = new UserRating();
		userRating.setListRatings(ratings);
		return userRating;
	}

}
