/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.query.user.*;

/**
 * Factory for the user queries.
 *
 * @see <a href="https://docs.api.jikan.moe/#tag/users">Jikan API docs - users</a>
 */
public class UserQueryFactory extends Factory {

	public UserQueryFactory(Jikan jikan) {
		super(jikan);
	}

	/**
	 * Get the user search.
	 *
	 * @return The user search query
	 * @see <a href="https://docs.api.jikan.moe/#operation/getUsersSearch">Jikan API docs - getUsersSearch</a>
	 */
	public UserSearchQuery search() {
		return new UserSearchQuery(this.jikan);
	}

	/**
	 * Get the user profile.
	 *
	 * @param username The username
	 * @return The user profile query
	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserProfile">Jikan API docs - getUserProfile</a>
	 */
	public UserProfileQuery profile(String username) {
		return new UserProfileQuery(this.jikan, username);
	}

	/**
	 * Get the user statistics.
	 *
	 * @param username The username
	 * @return The user statistics query
	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserStatistics">Jikan API docs - getUserStatistics</a>
	 */
	public UserStatisticsQuery statistics(String username) {
		return new UserStatisticsQuery(this.jikan, username);
	}

	/**
	 * Get the user favorites.
	 *
	 * @param username The username
	 * @return The user favorites query
	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserFavorites">Jikan API docs - getUserFavorites</a>
	 */
	public UserFavoritesQuery favorites(String username) {
		return new UserFavoritesQuery(this.jikan, username);
	}

	/**
	 * Get the user updates.
	 *
	 * @param username The username
	 * @return The user updates query
	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserUpdates">Jikan API docs - getUserUpdates</a>
	 */
	public UserUpdatesQuery updates(String username) {
		return new UserUpdatesQuery(this.jikan, username);
	}

	/**
	 * Get the user about.
	 *
	 * @param username The username
	 * @return The user about query
	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserAbout">Jikan API docs - getUserAbout</a>
	 */
	public UserAboutQuery about(String username) {
		return new UserAboutQuery(this.jikan, username);
	}

	/**
	 * Get the user recent history.
	 *
	 * @param username The username
	 * @return The user history query factory
	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserHistory">Jikan API docs - getUserHistory</a>
	 */
	public UserHistoryQueryFactory history(String username) {
		return new UserHistoryQueryFactory(this.jikan, username);
	}

	/**
	 * Get the user list of friends, if public.
	 *
	 * @param username The username
	 * @return The user friends query
	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserFriends">Jikan API docs - getUserFriends</a>
	 */
	public UserFriendsQuery friends(String username) {
		return new UserFriendsQuery(this.jikan, username);
	}

	/**
	 * Get a list of reviews written by the user.
	 *
	 * @param username The username
	 * @return The user reviews query
	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserReviews">Jikan API docs - getUserReviews</a>
	 */
	public UserReviewsQuery reviews(String username) {
		return new UserReviewsQuery(this.jikan, username);
	}

	/**
	 * Get the user recommendations.
	 *
	 * @param username The username
	 * @return The user recommendations query
	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserRecommendations">Jikan API docs - getUserRecommendations</a>
	 */
	public UserRecommendationsQuery recommendations(String username) {
		return new UserRecommendationsQuery(this.jikan, username);
	}

	/**
	 * Get the list of clubs the user joined.
	 *
	 * @param username The username
	 * @return The user clubs query
	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserClubs">Jikan API docs - getUserClubs</a>
	 */
	public UserClubsQuery clubs(String username) {
		return new UserClubsQuery(this.jikan, username);
	}
}
