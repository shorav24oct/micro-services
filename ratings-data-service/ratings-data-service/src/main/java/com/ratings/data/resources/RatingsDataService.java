package com.ratings.data.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratings.data.model.Rating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataService {
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(4, movieId);
	}

}
