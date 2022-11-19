/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The anime genre.
 */
public enum Genre {
	@JsonProperty("All")               ALL               (-2, -2, "All"),
	@JsonProperty("Action")            ACTION            ( 1,  1, "Action"),
	@JsonProperty("Adventure")         ADVENTURE         ( 2,  2, "Adventure"),
	@JsonProperty("Racing")            RACING            ( 3,  3, "Racing"),
	@JsonProperty("Comedy")            COMEDY            ( 4,  4, "Comedy"),
	@JsonProperty("Avant Garde")       AVANT_GARDE       ( 5,  5, "Avant Garde"),
	@JsonProperty("Mythology")         MYTHOLOGY         ( 6,  6, "Mythology"),
	@JsonProperty("Mystery")           MYSTERY           ( 7,  7, "Mystery"),
	@JsonProperty("Drama")             DRAMA             ( 8,  8, "Drama"),
	@JsonProperty("Ecchi")             ECCHI             ( 9,  9, "Ecchi"),
	@JsonProperty("Fantasy")           FANTASY           (10, 10, "Fantasy"),
	@JsonProperty("Strategy Game")     STRATEGY_GAME     (11, 11, "Strategy Game"),
	@JsonProperty("Hentai")            HENTAI            (12, 12, "Hentai"),
	@JsonProperty("Historical")        HISTORICAL        (13, 13, "Historical"),
	@JsonProperty("Horror")            HORROR            (14, 14, "Horror"),
	@JsonProperty("Kids")              KIDS              (15, 15, "Kids"),
	@JsonProperty("Martial Arts")      MARTIAL_ARTS      (17, 17, "Martial Arts"),
	@JsonProperty("Mecha")             MECHA             (18, 18, "Mecha"),
	@JsonProperty("Music")             MUSIC             (19, 19, "Music"),
	@JsonProperty("Parody")            PARODY            (20, 20, "Parody"),
	@JsonProperty("Samurai")           SAMURAI           (21, 21, "Samurai"),
	@JsonProperty("Romance")           ROMANCE           (22, 22, "Romance"),
	@JsonProperty("School")            SCHOOL            (23, 23, "School"),
	@JsonProperty("Sci-Fi")            SCI_FI            (24, 24, "Sci-Fi"),
	@JsonProperty("Shoujo")            SHOUJO            (25, 25, "Shoujo"),
	@JsonProperty("Girls Love")        GIRLS_LOVE        (26, 26, "Girls Love"),
	@JsonProperty("Shounen")           SHOUNEN           (27, 27, "Shounen"),
	@JsonProperty("Boys Love")         BOYS_LOVE         (28, 28, "Boys Love"),
	@JsonProperty("Space")             SPACE             (29, 29, "Space"),
	@JsonProperty("Sports")            SPORTS            (30, 30, "Sports"),
	@JsonProperty("Super Power")       SUPERPOWER        (31, 31, "Super Power"),
	@JsonProperty("Vampire")           VAMPIRE           (32, 32, "Vampire"),
	@JsonProperty("Harem")             HAREM             (35, 35, "Harem"),
	@JsonProperty("Slice of Life")     SLICE_OF_LIFE     (36, 36, "Slice of Life"),
	@JsonProperty("Supernatural")      SUPERNATURAL      (37, 37, "Supernatural"),
	@JsonProperty("Military")          MILITARY          (38, 38, "Military"),
	@JsonProperty("Detective")         DETECTIVE         (39, 39, "Detective"),
	@JsonProperty("Psychological")     PSYCHOLOGICAL     (40, 40, "Psychological"),
	@JsonProperty("Suspense")          SUSPENSE          (41, 45, "Suspense"),
	@JsonProperty("Seinen")            SEINEN            (42, 41, "Seinen"),
	@JsonProperty("Josei")             JOSEI             (43, 42, "Josei"),
	@JsonProperty("Award Winning")     AWARD_WINNING     (46, 46, "Award Winning"),
	@JsonProperty("Gourmet")           GOURMET           (47, 47, "Gourmet"),
	@JsonProperty("Workplace")         WORKPLACE         (48, 48, "Workplace"),
	@JsonProperty("Erotica")           EROTICA           (49, 49, "Erotica"),
	@JsonProperty("Adult Cast")        ADULT_CAST        (50, 50, "Adult Cast"),
	@JsonProperty("Anthropomorphic")   ANTHROPOMORPHIC   (51, 51, "Anthropomorphic"),
	@JsonProperty("CGDCT")             CGDCT             (52, 52, "CGDCT"),
	@JsonProperty("Childcare")         CHILDCARE         (53, 53, "Childcare"),
	@JsonProperty("Combat Sports")     COMBAT_SPORTS     (54, 54, "Combat Sports"),
	@JsonProperty("Delinquents")       DELINQUENTS       (55, 55, "Delinquents"),
	@JsonProperty("Educational")       EDUCATIONAL       (56, 56, "Educational"),
	@JsonProperty("Gag Humor")         GAG_HUMOR         (57, 57, "Gag Humor"),
	@JsonProperty("Gore")              GORE              (58, 58, "Gore"),
	@JsonProperty("High Stakes Game")  HIGH_STAKES_GAME  (59, 59, "High Stakes Game"),
	@JsonProperty("Idols (Female)")    IDOLS_FEMALE      (60, 60, "Idols (Female)"),
	@JsonProperty("Idols (Male)")      IDOLS_MALE        (61, 61, "Idols (Male)"),
	@JsonProperty("Isekai")            ISEKAI            (62, 62, "Isekai"),
	@JsonProperty("Iyashikei")         IYASHIKEI         (63, 63, "Iyashikei"),
	@JsonProperty("Love Polygon")      LOVE_POLYGON      (64, 64, "Love Polygon"),
	@JsonProperty("Magical Sex Shift") MAGICAL_SEX_SHIFT (65, 65, "Magical Sex Shift"),
	@JsonProperty("Mahou Shoujo")      MAHOU_SHOUJO      (66, 66, "Mahou Shoujo"),
	@JsonProperty("Medical")           MEDICAL           (67, 67, "Medical"),
	@JsonProperty("Memoir")            MEMOIR            (-3, 68, "Memoir"),
	@JsonProperty("Organized Crime")   ORGANIZED_CRIME   (68, 69, "Organized Crime"),
	@JsonProperty("Otaku Culture")     OTAKU_CULTURE     (69, 70, "Otaku Culture"),
	@JsonProperty("Performing Arts")   PERFORMING_ARTS   (70, 71, "Performing Arts"),
	@JsonProperty("Pets")              PETS              (71, 72, "Pets"),
	@JsonProperty("Reincarnation")     REINCARNATION     (72, 73, "Reincarnation"),
	@JsonProperty("Reverse Harem")     REVERSE_HAREM     (73, 74, "Reverse Harem"),
	@JsonProperty("Romantic Subtext")  ROMANTIC_SUBTEXT  (74, 75, "Romantic Subtext"),
	@JsonProperty("Showbiz")           SHOWBIZ           (75, 76, "Showbiz"),
	@JsonProperty("Survival")          SURVIVAL          (76, 77, "Survival"),
	@JsonProperty("Team Sports")       TEAM_SPORTS       (77, 78, "Team Sports"),
	@JsonProperty("Time Travel")       TIME_TRAVEL       (78, 79, "Time Travel"),
	@JsonProperty("Video Game")        VIDEO_GAME        (79, 80, "Video Game"),
	@JsonProperty("Visual Arts")       VISUAL_ARTS       (80, 82, "Visual Arts"),
	@JsonProperty("Crossdressing")     CROSSDRESSING     (81, 44, "Crossdressing"),
	@JsonProperty("Villainess")        VILLAINESS        (-5, 81, "Villainess"),
	/** Catch-all tag. If you see this value, please open an issue on the GitHub issue tracker. */
	@JsonEnumDefaultValue              UNKNOWN           (-1, -1, "?");

	/** The MAL anime ID. */
	public final int animeId;
	/** The MAL manga ID. */
	public final int mangaId;
	/** The genre name, as shown by MAL. */
	public final String displayName;

	Genre                        (int animeId, int mangaId, String displayName) {
		this.animeId = animeId;
		this.mangaId = mangaId;
		this.displayName = displayName;
	}

	public int animeId                        () {
		return animeId;
	}

	public int mangaId                        () {
		return mangaId;
	}

	public String displayName                        () {
		return displayName;
	}
}
