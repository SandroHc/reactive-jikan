/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.person;

import net.sandrohc.jikan.model.*;

/**
 * A person's voice acting role for a given character on an anime.
 */
public class PersonVoiceActingRole extends PersonRole {

    /** The character. */
    public EntityWithImage character;


    public EntityWithImage getCharacter() {
        return character;
    }

    public void setCharacter(EntityWithImage character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return "PersonVoiceActingRole[role='" + role + "', entry=" + entry + ", character=" + character + ']';
    }
}
