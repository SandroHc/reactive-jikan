package net.sandrohc.jikan.model.anime;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.character.*;

/**
 * A MyAnimeList anime character.
 */
public class AnimeCharacter extends RoleSubEntity {

	@JsonProperty("voice_actors")
	public List<CharacterVoiceActor> voiceActors;

}
