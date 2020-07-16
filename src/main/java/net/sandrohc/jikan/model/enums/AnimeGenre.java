/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AnimeGenre {
	@JsonProperty("All")           ALL,
	@JsonProperty("Action")        ACTION,
	@JsonProperty("Adventure")     ADVENTURE,
	@JsonProperty("Cars")          CARS,
	@JsonProperty("Comedy")        COMEDY,
	@JsonProperty("Dementia")      DEMENTIA,
	@JsonProperty("Demons")        DEMONS,
	@JsonProperty("Mystery")       MYSTERY,
	@JsonProperty("Drama")         DRAMA,
	@JsonProperty("Ecchi")         ECCHI,
	@JsonProperty("Fantasy")       FANTASY,
	@JsonProperty("Game")          GAME,
	@JsonProperty("Hentai")        HENTAI,
	@JsonProperty("Historical")    HISTORICAL,
	@JsonProperty("Horror")        HORROR,
	@JsonProperty("Kids")          KIDS,
	@JsonProperty("Magic")         MAGIC,
	@JsonProperty("Martial Arts")  MARTIAL_ARTS,
	@JsonProperty("Mecha")         MECHA,
	@JsonProperty("Music")         MUSIC,
	@JsonProperty("Parody")        PARODY,
	@JsonProperty("Samurai")       SAMURAI,
	@JsonProperty("Romance")       ROMANCE,
	@JsonProperty("School")        SCHOOL,
	@JsonProperty("Sci-Fi")        SCI_FI,
	@JsonProperty("Shoujo")        SHOUJO,
	@JsonProperty("Shoujo Ai")     SHOUJO_AI,
	@JsonProperty("Shounen")       SHOUNEN,
	@JsonProperty("Shounen Ai")    SHOUNEN_AI,
	@JsonProperty("Space")         SPACE,
	@JsonProperty("Sports")        SPORTS,
	@JsonProperty("Super Power")   SUPERPOWER,
	@JsonProperty("Vampire")       VAMPIRE,
	@JsonProperty("Yaoi")          YAOI,
	@JsonProperty("Yuri")          YURI,
	@JsonProperty("Harem")         HAREM,
	@JsonProperty("Slice of Life") SLICE_OF_LIFE,
	@JsonProperty("Supernatural")  SUPERNATURAL,
	@JsonProperty("Military")      MILITARY,
	@JsonProperty("Police")        POLICE,
	@JsonProperty("Psychological") PSYCHOLOGICAL,
	@JsonProperty("Thriller")      THRILLER,
	@JsonProperty("Seinen")        SEINEN,
	@JsonProperty("Josei")         JOSEI,
}
