/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.character;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.QueryMono;

public class CharacterPicturesQuery extends QueryMono<Pictures> {

	private final int id;

	public CharacterPicturesQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUri() {
		return "/character/" + id + "/pictures";
	}

	@Override
	public Class<Pictures> getRequestClass() {
		return Pictures.class;
	}

}
