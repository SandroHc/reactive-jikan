/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.character;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;

public class CharacterTopSub extends MalEntity {

    public int rank;

    public String title;

    public String url;

    @JsonProperty("image_url")
    public String imageUrl;

    @JsonProperty("name_kanji")
    public String nameKanji;

    public int favorites;

    public List<MalSubEntity> animeography;

    public List<MalSubEntity> mangaography;


    @Override
    public String toString() {
        return "CharacterTopSub[id=" + malId + ", title='" + title + "']";
    }

}
