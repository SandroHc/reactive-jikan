/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.character;

import java.io.*;

import net.sandrohc.jikan.model.person.*;
import net.sandrohc.jikan.utils.Generated;

public class CharacterVoiceActor implements Serializable {

	/** The voice actor details. */
	public PersonSimple person;

	/** The language performed by the voice actor. */
	public String language;


	public PersonSimple getPerson() {
		return person;
	}

	public void setPerson(PersonSimple person) {
		this.person = person;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CharacterVoiceActor that = (CharacterVoiceActor) o;

		if (person != null ? !person.equals(that.person) : that.person != null) return false;
		return language != null ? language.equals(that.language) : that.language == null;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = person != null ? person.hashCode() : 0;
		result = 31 * result + (language != null ? language.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "AnimeCharacterVoiceActor[person=" + person + ", language='" + language + "']";
	}

}
