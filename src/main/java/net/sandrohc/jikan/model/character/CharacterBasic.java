/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.character;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.utils.Generated;

/**
 * A character.
 */
public class CharacterBasic implements Serializable {

	/** The character details. */
	public CharacterDetails character;

	/** The character role, e.g. 'Main'. */
	public CharacterRole role;

	/** The character voice actors. */
	@JsonProperty("voice_actors")
	public List<CharacterVoiceActor> voiceActors = Collections.emptyList();


	public CharacterDetails getCharacter() {
		return character;
	}

	public void setCharacter(CharacterDetails character) {
		this.character = character;
	}

	public CharacterRole getRole() {
		return role;
	}

	public void setRole(CharacterRole role) {
		this.role = role;
	}

	public List<CharacterVoiceActor> getVoiceActors() {
		return voiceActors;
	}

	public void setVoiceActors(List<CharacterVoiceActor> voiceActors) {
		this.voiceActors = voiceActors;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CharacterBasic that = (CharacterBasic) o;

		if (character != null ? !character.equals(that.character) : that.character != null) return false;
		if (role != null ? !role.equals(that.role) : that.role != null) return false;
		return voiceActors != null ? voiceActors.equals(that.voiceActors) : that.voiceActors == null;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = character != null ? character.hashCode() : 0;
		result = 31 * result + (role != null ? role.hashCode() : 0);
		result = 31 * result + (voiceActors != null ? voiceActors.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "AnimeCharacter[character=" + character + ", role='" + role + '\'' + ", voiceActors=" + voiceActors + ']';
	}
}
