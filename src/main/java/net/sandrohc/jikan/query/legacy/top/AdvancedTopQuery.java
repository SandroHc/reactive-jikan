/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.top;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.enums.*;

@SuppressWarnings("unchecked")
public abstract class AdvancedTopQuery<Q extends AdvancedTopQuery<Q, T, SUBTYPE>, T, SUBTYPE> extends TopQuery<T> {

	private SUBTYPE subtype;

	protected AdvancedTopQuery(Jikan jikan, Type type, int page) throws JikanInvalidArgumentException {
		super(jikan, type, page);
	}

	public Q subtype(SUBTYPE subtype) {
		this.subtype = subtype;
		return (Q) this;
	}

	@Override
	public String getUri() {
		if (subtype != null)
			return super.getUri() + '/' + subtype.toString();
		else
			return super.getUri();
	}

}
