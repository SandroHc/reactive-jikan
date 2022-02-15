/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;

/**
 * Factory for the user queries.
 *
 * @see <a href="https://docs.api.jikan.moe/#tag/users">Jikan API docs - users</a>
 */
public class UserQueryFactory extends Factory {

	public UserQueryFactory(Jikan jikan) {
		super(jikan);
	}

	// TODO: implement
//	/**
//	 * Get the user details.
//	 *
//	 * @param userId The user ID
//	 * @return The user query factory
//	 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeById">Jikan API docs - getAnimeById</a>
//	 */
//	public UserQuery get(int userId) {
//		return new UserQuery(this.jikan, userId);
//	}

//	/**
//	 * Get the user search.
//	 *
//	 * @return The user search query factory
//	 * @see <a href="https://docs.api.jikan.moe/#operation/getUsersSearch">Jikan API docs - getUsersSearch</a>
//	 */
//	public UserSearchQuery search() {
//		return new UserSearchQuery(this.jikan);
//	}

//	/**
//	 * Get the user profile.
//	 *
//	 * @param username The username
//	 * @return The user profile query factory
//	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserProfile">Jikan API docs - getUserProfile</a>
//	 */
//	public UserProfileQuery profile(String username) {
//		return new UserProfileQuery(this.jikan, username);
//	}

//	/**
//	 * Get the user statistics.
//	 *
//	 * @param username The username
//	 * @return The user statistics query factory
//	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserStatistics">Jikan API docs - getUserStatistics</a>
//	 */
//	public UserStatisticsQuery statistics(String username) {
//		return new UserStatisticsQuery(this.jikan, username);
//	}

//	/**
//	 * Get the user favorites.
//	 *
//	 * @param username The username
//	 * @return The user favorites query factory
//	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserFavorites">Jikan API docs - getUserFavorites</a>
//	 */
//	public UserFavoritesQuery favorites(String username) {
//		return new UserFavoritesQuery(this.jikan, username);
//	}

//	/**
//	 * Get the user updates.
//	 *
//	 * @param username The username
//	 * @return The user updates query factory
//	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserUpdates">Jikan API docs - getUserUpdates</a>
//	 */
//	public UserUpdatesQuery updates(String username) {
//		return new UserUpdatesQuery(this.jikan, username);
//	}

//	/**
//	 * Get the user about.
//	 *
//	 * @param username The username
//	 * @return The user about query factory
//	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserAbout">Jikan API docs - getUserAbout</a>
//	 */
//	public UserAboutQuery about(String username) {
//		return new UserAboutQuery(this.jikan, username);
//	}

//	/**
//	 * Get the user history.
//	 *
//	 * @param username The username
//	 * @return The user history query factory
//	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserHistory">Jikan API docs - getUserHistory</a>
//	 */
//	public UserHistoryQuery history(String username) {
//		return new UserHistoryQuery(this.jikan, username);
//	}

//	/**
//	 * Get the user friends.
//	 *
//	 * @param username The username
//	 * @return The user friends query factory
//	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserFroemds">Jikan API docs - getUserFroemds</a>
//	 */
//	public UserFroemdsQuery friends(String username) {
//		return new UserFroemdsQuery(this.jikan, username);
//	}

//	/**
//	 * Get the user reviews.
//	 *
//	 * @param username The username
//	 * @return The user reviews query factory
//	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserReviews">Jikan API docs - getUserReviews</a>
//	 */
//	public UserReviewsQuery reviews(String username) {
//		return new UserReviewsQuery(this.jikan, username);
//	}

//	/**
//	 * Get the user favorites.
//	 *
//	 * @param username The username
//	 * @return The user favorites query factory
//	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserFavorites">Jikan API docs - getUserFavorites</a>
//	 */
//	public UserFavoritesQuery favorites(String username) {
//		return new UserFavoritesQuery(this.jikan, username);
//	}

//	/**
//	 * Get the user recommendations.
//	 *
//	 * @param username The username
//	 * @return The user recommendations query factory
//	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserRecommendations">Jikan API docs - getUserRecommendations</a>
//	 */
//	public UserRecommendationsQuery recommendations(String username) {
//		return new UserRecommendationsQuery(this.jikan, username);
//	}

//	/**
//	 * Get the user clubs.
//	 *
//	 * @param username The username
//	 * @return The user clubs query factory
//	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserClubs">Jikan API docs - getUserClubs</a>
//	 */
//	public UserClubsQuery clubs(String username) {
//		return new UserClubsQuery(this.jikan, username);
//	}
}
