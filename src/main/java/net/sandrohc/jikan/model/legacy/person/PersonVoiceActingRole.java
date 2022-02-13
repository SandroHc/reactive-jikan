/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.person;

import java.io.*;

import net.sandrohc.jikan.model.legacy.base.*;

/**
 * A person's voice acting role for a given character on an anime.
 */
public class PersonVoiceActingRole implements Serializable {

    public String role;

    public MalSubEntity anime;

    public MalSubEntity character;


    @Override
    public String toString() {
        return "PersonVoiceActingRole[role='" + role + "', anime=" + anime + ", character=" + character + "]";
    }

}
