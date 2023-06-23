package com.cognizant.UserManagement.config;

public final class Paths {

	public Paths() {
	}

	private static final String url = "/api/v1/movieBooking/";

	protected static final String[] EXCULDED_PATHS = { url + "generate-token", url + "authorize", url + "register",
			url + "forgotPassword", url + "/swagger-ui/**" };

	protected static final String[] ADMIN_PATHS = { url + "admin/**" };

	protected static final String[] USER_PATHS = { url + "user/**" };

}
