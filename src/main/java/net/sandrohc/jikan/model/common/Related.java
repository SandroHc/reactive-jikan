/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;

/**
 * A list of related anime or manga.
 */
public class Related {

	@JsonProperty("Prequel")
	public List<MalSubEntity> prequels = Collections.emptyList();

	@JsonProperty("Alternative version")
	public List<MalSubEntity> alternativeVersions = Collections.emptyList();

	@JsonProperty("Alternative setting")
	public List<MalSubEntity> alternativeSettings = Collections.emptyList();

	@JsonProperty("Character")
	public List<MalSubEntity> characters = Collections.emptyList();

	@JsonProperty("Spin-off")
	public List<MalSubEntity> spinOffs = Collections.emptyList();

	@JsonProperty("Adaptation")
	public List<MalSubEntity> adaptations = Collections.emptyList();

	@JsonProperty("Summary")
	public List<MalSubEntity> summaries = Collections.emptyList();

	@JsonProperty("Sequel")
	public List<MalSubEntity> sequels = Collections.emptyList();

	@JsonProperty("Side story")
	public List<MalSubEntity> sideStories = Collections.emptyList();

	@JsonProperty("Other")
	public List<MalSubEntity> others = Collections.emptyList();

	@JsonProperty("Parent story")
	public List<MalSubEntity> parentStories = Collections.emptyList();

	@JsonProperty("Full story")
	public List<MalSubEntity> fullStories = Collections.emptyList();

}
