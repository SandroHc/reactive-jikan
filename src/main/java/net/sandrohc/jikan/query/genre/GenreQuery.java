/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.genre;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.QueryFlux;

public abstract class GenreQuery<C extends GenreQuery<C,TYPE_INITIAL,TYPE_FINAL,GENRE>, TYPE_INITIAL, TYPE_FINAL, GENRE extends Enum<?>> extends QueryFlux<TYPE_INITIAL, TYPE_FINAL> {

	/** The type of the results. */
	public final Type type;

	/** The genre os results. */
	public final GENRE genre;

	/** The page. Each page contains up to 50 results. */
	public final int page;

	protected GenreQuery(Jikan jikan, Type type, GENRE genre, int page) throws JikanInvalidArgumentException {
		super(jikan);
		if (page < 1) throw new JikanInvalidArgumentException("page starts at index 1");

		this.type = type;
		this.genre = genre;
		this.page = page;
	}

	@Override
	public String getUri() {
		return "/genre/" + type.genre + '/' + genre.ordinal() + '/' + page;
	}

}
