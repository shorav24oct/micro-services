package com.movie.catalog.model;

import java.util.List;

public class UserRating {

	public UserRating() {
		// TODO Auto-generated constructor stub
	}

	private List<Rating> listRatings;
	private String userId;

	public List<Rating> getListRatings() {
		return listRatings;
	}

	public void setListRatings(List<Rating> listRatings) {
		this.listRatings = listRatings;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
