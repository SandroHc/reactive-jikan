package net.sandrohc.jikan.model.anime;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.MalEntity;

public class AnimeCharacterVoiceActor extends MalEntity {

	public String name;

	public String url;

	@JsonProperty("image_url")
	public String imageUrl;

	public String language;



	@Override
	public String toString() {
		return "AnimeCharacterVoiceActor[" +
			   "id=" + malId +
			   ", name='" + name + '\'' +
			   ']';
	}
}
