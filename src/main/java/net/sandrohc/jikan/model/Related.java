/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;

public class Related {

	@JsonProperty("Prequel")
	public List<MalSubEntity> prequels;

	@JsonProperty("Alternative version")
	public List<MalSubEntity> alternativeVersions;

	@JsonProperty("Alternative setting")
	public List<MalSubEntity> alternativeSettings;

	@JsonProperty("Character")
	public List<MalSubEntity> characters;

	@JsonProperty("Spin-off")
	public List<MalSubEntity> spinOffs;

	@JsonProperty("Adaptation")
	public List<MalSubEntity> adaptations;

	@JsonProperty("Summary")
	public List<MalSubEntity> summaries;

	@JsonProperty("Sequel")
	public List<MalSubEntity> sequels;

	@JsonProperty("Side story")
	public List<MalSubEntity> sideStories;

	@JsonProperty("Other")
	public List<MalSubEntity> others;

	@JsonProperty("Parent story")
	public List<MalSubEntity> parentStories;

	@JsonProperty("Full story")
	public List<MalSubEntity> fullStories;

}
