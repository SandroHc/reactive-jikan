package net.sandrohc.jikan.model.legacy.anime;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.MalEntity;

/**
 * A staff member.
 */
public class AnimeStaff extends MalEntity {

	/** The URL to the staff on MyAnimeList. */
	public String url;

	/** The staff name. */
	public String name;

	@JsonProperty("image_url")
	public String imageUrl;

	public List<String> positions;


	@Override
	public String toString() {
		return "AnimeStaff[id=" + malId + ", name='" + name + "']";
	}

}
