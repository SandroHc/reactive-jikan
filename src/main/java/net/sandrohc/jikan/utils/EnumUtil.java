/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.utils;

import java.util.*;
import java.util.stream.*;

public class EnumUtil {

	private EnumUtil() { }

	public static Collection<Integer> enumsToOrdinals(Enum<?>... enumValues) {
		if (enumValues == null)
			return Collections.emptyList();

		return Collections.unmodifiableCollection(Arrays.stream(enumValues)
				.map(Enum::ordinal)
				.collect(Collectors.toCollection(TreeSet::new)));
	}
}
