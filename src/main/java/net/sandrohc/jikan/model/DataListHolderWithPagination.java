/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model;

import net.sandrohc.jikan.utils.Generated;

public class DataListHolderWithPagination<T> extends DataListHolder<T> {

	public Pagination pagination;


	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		DataListHolderWithPagination<?> that = (DataListHolderWithPagination<?>) o;

		return pagination != null ? pagination.equals(that.pagination) : that.pagination == null;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (pagination != null ? pagination.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "DataPaginationHolder[pagination=" + pagination + ", data=" + data + ']';
	}
}
