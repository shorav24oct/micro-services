package com.movie.info.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.info.model.MovieInfo;

@RestController
@RequestMapping("/movieinfo")
public class MovieInfoResource {

	@RequestMapping("/{movieId}")
	public MovieInfo getMovieInfo(@PathVariable("movieId") String movieId) {
		return new MovieInfo(movieId, "testing movie");
	}

}
