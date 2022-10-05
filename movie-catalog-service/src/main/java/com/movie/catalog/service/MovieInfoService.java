package com.movie.catalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.movie.catalog.model.CatalogItem;
import com.movie.catalog.model.MovieInfo;
import com.movie.catalog.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MovieInfoService {

	@Autowired
	private RestTemplate restTemplate;

	/*
	 * use small case for writing the application name MOVIE-INFO-SERVICE:
	 * movie-info-service
	 */
	private static final String MOVIE_INFO_URL = "http://movie-info-service/movieinfo/";

	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem",
			threadPoolKey = "movieInfoPool",
			threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "20"),
					@HystrixProperty(name = "maxQueueSize", value = "10")
			}
			)
	public CatalogItem getCatalogItem(Rating rating) {
		MovieInfo movieInfo = restTemplate.getForObject(MOVIE_INFO_URL + rating.getMovieId(), MovieInfo.class);

		return new CatalogItem(rating.getRating(), movieInfo.getName(), "Revenge of the Fallen");
	}

	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem(rating.getRating(), "Movie Name not found", "");

	}

}
