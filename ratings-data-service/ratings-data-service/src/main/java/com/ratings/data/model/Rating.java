package com.ratings.data.model;

public class Rating {

	private int ratings;
	private String movieId;

	public Rating() {

	}

	public Rating(int ratings, String movieId) {
		this.ratings = ratings;
		this.movieId = movieId;
	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

}
