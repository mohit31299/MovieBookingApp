package com.cognizant.MovieBookingApp.Enum;

public enum KafkaProducerMessage {

	ADD_MOVIE("New Movie added successfully.", "Failed to add new movie.", "Add movie API is not authorized."),

	FIND_MOVIE_BY_NAME("Movie find successfully", "Movie not found.", "Search movie by name API is not authorized."),

	DELETE_MOVIE("Movie deleted successfully.", "Failed to delete", "Delete movie API is not authorized."),

	FIND_ALL_MOVIE("All movie fetched successfully", "Movie list is empty.", "Find all movie API is not authorized."),

	UPDATE_MOVIE("Movie update successfully", "Movie not updated.", "Update movie API is not authorized"),

	BOOK_MOVIE("Movie booked successfully", "Error in booking movie.", "Book movie API is not authorized"),
	
	DELETE_TRANSACTION("Transaction deleted successfully", "Transaction not deleted", "Delete transaction API is not authorized");

	private String success;
	private String failed;
	private String authorizeStatus;

	private KafkaProducerMessage(String success, String failed, String authorizeStatus) {
		this.success = success;
		this.failed = failed;
		this.authorizeStatus = authorizeStatus;
	}

	public String getSuccess() {
		return success;
	}

	public String getFailed() {
		return failed;
	}

	public String getAuthorizeStatus() {
		return authorizeStatus;
	}

}
