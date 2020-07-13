package net.sandrohc.jikan.model.anime;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.MalEntity;

public class AnimeStaff extends MalEntity {

	public String url;

	public String name;

	@JsonProperty("image_url")
	public String imageUrl;

	public List<String> positions; // TODO: convert to enum


	@Override
	public String toString() {
		return "AnimeStaff[" +
			   "id=" + malId +
			   ", name='" + name + '\'' +
			   ']';
	}

}
