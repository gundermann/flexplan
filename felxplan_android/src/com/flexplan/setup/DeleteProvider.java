package com.flexplan.setup;


public interface DeleteProvider<T> {

	void delete(T o);

	void initDelete(T o);
}
