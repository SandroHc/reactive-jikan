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
	@JsonProperty("MartialArts")   MARTIAL_ARTS,
	@JsonProperty("Mecha")         MECHA,
	@JsonProperty("Music")         MUSIC,
	@JsonProperty("Parody")        PARODY,
	@JsonProperty("Samurai")       SAMURAI,
	@JsonProperty("Romance")       ROMANCE,
	@JsonProperty("School")        SCHOOL,
	@JsonProperty("SciFi")         SCI_FI,
	@JsonProperty("Shoujo")        SHOUJO,
	@JsonProperty("ShoujoAi")      SHOUJO_AI,
	@JsonProperty("Shounen")       SHOUNEN,
	@JsonProperty("ShounenAi")     SHOUNEN_AI,
	@JsonProperty("Space")         SPACE,
	@JsonProperty("Sports")        SPORTS,
	@JsonProperty("SuperPower")    SUPERPOWER,
	@JsonProperty("Vampire")       VAMPIRE,
	@JsonProperty("Yaoi")          YAOI,
	@JsonProperty("Yuri")          YURI,
	@JsonProperty("Harem")         HAREM,
	@JsonProperty("SliceOfLife")   SLICE_OF_LIFE,
	@JsonProperty("Supernatural")  SUPERNATURAL,
	@JsonProperty("Military")      MILITARY,
	@JsonProperty("Police")        POLICE,
	@JsonProperty("Psychological") PSYCHOLOGICAL,
	@JsonProperty("Thriller")      THRILLER,
	@JsonProperty("Seinen")        SEINEN,
	@JsonProperty("Josei")         JOSEI,
}
