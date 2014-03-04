package com.flexplan.util;


public interface DeleteProvider<T> {

	void delete(T o);

	void initDelete(T o);
}
