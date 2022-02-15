/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.query.club.*;

/**
 * Factory for the club queries.
 *
 * @see <a href="https://docs.api.jikan.moe/#tag/clubs">Jikan API docs - clubs</a>
 */
public class ClubQueryFactory extends Factory {

    public ClubQueryFactory(Jikan jikan) {
        super(jikan);
    }

    /**
     * Get the club details.
     *
     * @param clubId The club ID
     * @return The club query
     * @see <a href="https://docs.api.jikan.moe/#operation/getClubsById">Jikan API docs - getClubsById</a>
     */
    public ClubQuery get(int clubId) {
        return new ClubQuery(this.jikan, clubId);
    }

    /**
     * Get the club search.
     *
     * @return The club search query
     * @see <a href="https://docs.api.jikan.moe/#operation/getClubSearch">Jikan API docs - getClubSearch</a>
     */
    public ClubSearchQuery search() {
        return new ClubSearchQuery(this.jikan);
    }

    /**
     * Get the club members.
     *
     * @param clubId The club ID
     * @return The club members query
     * @see <a href="https://docs.api.jikan.moe/#operation/getClubMembers">Jikan API docs - getClubMembers</a>
     */
    public ClubMembersQuery members(int clubId) {
        return new ClubMembersQuery(this.jikan, clubId);
    }

    /**
     * Get the club staff.
     *
     * @param clubId The club ID
     * @return The club staff query
     * @see <a href="https://docs.api.jikan.moe/#operation/getClubStaff">Jikan API docs - getClubStaff</a>
     */
    public ClubStaffQuery staff(int clubId) {
        return new ClubStaffQuery(this.jikan, clubId);
    }

    /**
     * Get the club relations.
     *
     * @param clubId The club ID
     * @return The club relations query
     * @see <a href="https://docs.api.jikan.moe/#operation/getClubRelations">Jikan API docs - getClubRelations</a>
     */
    public ClubRelationsQuery relations(int clubId) {
        return new ClubRelationsQuery(this.jikan, clubId);
    }
}
