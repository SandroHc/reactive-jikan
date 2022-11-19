/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The anime genre.
 */
public enum AnimeGenre {
	@JsonProperty("All")               ALL(-2, "All"),
	@JsonProperty("Action")            ACTION(1, "Action"),
	@JsonProperty("Adventure")         ADVENTURE(2, "Adventure"),
	@JsonProperty("Racing")            RACING(3, "Racing"),
	@JsonProperty("Comedy")            COMEDY(4, "Comedy"),
	@JsonProperty("Avant Garde")       AVANT_GARDE(5, "Avant Garde"),
	@JsonProperty("Mythology")         MYTHOLOGY(6, "Mythology"),
	@JsonProperty("Mystery")           MYSTERY(7, "Mystery"),
	@JsonProperty("Drama")             DRAMA(8, "Drama"),
	@JsonProperty("Ecchi")             ECCHI(9, "Ecchi"),
	@JsonProperty("Fantasy")           FANTASY(10, "Fantasy"),
	@JsonProperty("Strategy Game")     STRATEGY_GAME(11, "Strategy Game"),
	@JsonProperty("Hentai")            HENTAI(12, "Hentai"),
	@JsonProperty("Historical")        HISTORICAL(13, "Historical"),
	@JsonProperty("Horror")            HORROR(14, "Horror"),
	@JsonProperty("Kids")              KIDS(15, "Kids"),
	@JsonProperty("Martial Arts")      MARTIAL_ARTS(17, "Martial Arts"),
	@JsonProperty("Mecha")             MECHA(18, "Mecha"),
	@JsonProperty("Music")             MUSIC(19, "Music"),
	@JsonProperty("Parody")            PARODY(20, "Parody"),
	@JsonProperty("Samurai")           SAMURAI(21, "Samurai"),
	@JsonProperty("Romance")           ROMANCE(22, "Romance"),
	@JsonProperty("School")            SCHOOL(23, "School"),
	@JsonProperty("Sci-Fi")            SCI_FI(24, "Sci-Fi"),
	@JsonProperty("Shoujo")            SHOUJO(25, "Shoujo"),
	@JsonProperty("Girls Love")        GIRLS_LOVE(26, "Girls Love"),
	@JsonProperty("Shounen")           SHOUNEN(27, "Shounen"),
	@JsonProperty("Boys Love")         BOYS_LOVE(28, "Boys Love"),
	@JsonProperty("Space")             SPACE(29, "Space"),
	@JsonProperty("Sports")            SPORTS(30, "Sports"),
	@JsonProperty("Super Power")       SUPERPOWER(31, "Super Power"),
	@JsonProperty("Vampire")           VAMPIRE(32, "Vampire"),
	@JsonProperty("Harem")             HAREM(35, "Harem"),
	@JsonProperty("Slice of Life")     SLICE_OF_LIFE(36, "Slice of Life"),
	@JsonProperty("Supernatural")      SUPERNATURAL(37, "Supernatural"),
	@JsonProperty("Military")          MILITARY(38, "Military"),
	@JsonProperty("Detective")         DETECTIVE(39, "Detective"),
	@JsonProperty("Psychological")     PSYCHOLOGICAL(40, "Psychological"),
	@JsonProperty("Suspense")          SUSPENSE(41, "Suspense"),
	@JsonProperty("Seinen")            SEINEN(42, "Seinen"),
	@JsonProperty("Josei")             JOSEI(43, "Josei"),
	@JsonProperty("Award Winning")     AWARD_WINNING(46, "Award Winning"),
	@JsonProperty("Gourmet")           GOURMET(47, "Gourmet"),
	@JsonProperty("Workplace")         WORKPLACE(48, "Workplace"),
	@JsonProperty("Erotica")           EROTICA(49, "Erotica"),
	@JsonProperty("Adult Cast")        ADULT_CAST(50, "Adult Cast"),
	@JsonProperty("Anthropomorphic")   ANTHROPOMORPHIC(51, "Anthropomorphic"),
	@JsonProperty("CGDCT")             CGDCT(52, "CGDCT"),
	@JsonProperty("Childcare")         CHILDCARE(53, "Childcare"),
	@JsonProperty("Combat Sports")     COMBAT_SPORTS(54, "Combat Sports"),
	@JsonProperty("Delinquents")       DELINQUENTS(55, "Delinquents"),
	@JsonProperty("Educational")       EDUCATIONAL(56, "Educational"),
	@JsonProperty("Gag Humor")         GAG_HUMOR(57, "Gag Humor"),
	@JsonProperty("Gore")              GORE(58, "Gore"),
	@JsonProperty("High Stakes Game")  HIGH_STAKES_GAME(59, "High Stakes Game"),
	@JsonProperty("Idols (Female)")    IDOLS_FEMALE(60, "Idols (Female)"),
	@JsonProperty("Idols (Male)")      IDOLS_MALE(61, "Idols (Male)"),
	@JsonProperty("Isekai")            ISEKAI(62, "Isekai"),
	@JsonProperty("Iyashikei")         IYASHIKEI(63, "Iyashikei"),
	@JsonProperty("Love Polygon")      LOVE_POLYGON(64, "Love Polygon"),
	@JsonProperty("Magical Sex Shift") MAGICAL_SEX_SHIFT(65, "Magical Sex Shift"),
	@JsonProperty("Mahou Shoujo")      MAHOU_SHOUJO(66, "Mahou Shoujo"),
	@JsonProperty("Medical")           MEDICAL(67, "Medical"),
	@JsonProperty("Organized Crime")   ORGANIZED_CRIME(68, "Organized Crime"),
	@JsonProperty("Otaku Culture")     OTAKU_CULTURE(69, "Otaku Culture"),
	@JsonProperty("Performing Arts")   PERFORMING_ARTS(70, "Performing Arts"),
	@JsonProperty("Pets")              PETS(71, "Pets"),
	@JsonProperty("Reincarnation")     REINCARNATION(72, "Reincarnation"),
	@JsonProperty("Reverse Harem")     REVERSE_HAREM(73, "Reverse Harem"),
	@JsonProperty("Romantic Subtext")  ROMANTIC_SUBTEXT(74, "Romantic Subtext"),
	@JsonProperty("Showbiz")           SHOWBIZ(75, "Showbiz"),
	@JsonProperty("Survival")          SURVIVAL(76, "Survival"),
	@JsonProperty("Team Sports")       TEAM_SPORTS(77, "Team Sports"),
	@JsonProperty("Time Travel")       TIME_TRAVEL(78, "Time Travel"),
	@JsonProperty("Video Game")        VIDEO_GAME(79, "Video Game"),
	@JsonProperty("Visual Arts")       VISUAL_ARTS(80, "Visual Arts"),
	@JsonProperty("Crossdressing")     CROSSDRESSING(81, "Crossdressing"),
	/** Catch-all tag. If you see this value, please open an issue on the GitHub issue tracker. */
	@JsonEnumDefaultValue              UNKNOWN(-1, "?");

	/** The MAL ID. */
	public final int id;
	/** The genre name, as shown by MAL. */
	public final String displayName;

	AnimeGenre(int id, String displayName) {
		this.id = id;
		this.displayName = displayName;
	}

	public int id() {
		return id;
	}

	public String displayName() {
		return displayName;
	}
}
