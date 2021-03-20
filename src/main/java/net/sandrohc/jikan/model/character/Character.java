/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.character;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;

/**
 * A character object with all its details.
 */
public class Character extends MalEntity {

    /** The URL to the character on MyAnimeList. */
    public String url;

    /** The banner image URL. */
    @JsonProperty("image_url")
    public String imageUrl;

    /** The official name. */
    public String name;

    /** The official name, in kanji. */
    @JsonProperty("name_kanji")
    public String nameKanji;

    public List<String> nicknames;

    public String about;

    @JsonProperty("member_favourites")
    public int memberFavourites;

    public List<RoleSubEntity> animeography = Collections.emptyList();

    public List<RoleSubEntity> mangaography = Collections.emptyList();

    @JsonProperty("voice_actors")
    public List<CharacterVoiceActor> voiceActors = Collections.emptyList();


    @Override
    public String toString() {
        return "Character[id=" + malId + ", name='" + name + "']";
    }

}
