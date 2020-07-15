/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.character;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;

public class Character extends MalEntity {

    public String url;

    public String name;

    @JsonProperty("name_kanji")
    public String nameKanji;

    public List<String> nicknames;

    public String about;

    @JsonProperty("member_favourites")
    public int memberFavourites;

    @JsonProperty("image_url")
    public String imageUrl;

    public List<RoleSubEntity> animeography;

    public List<RoleSubEntity> mangaography;

    @JsonProperty("voice_actors")
    public List<CharacterVoiceActor> voiceActors;


    @Override
    public String toString() {
        return "Character[id=" + malId + ", name='" + name + "']";
    }

}
