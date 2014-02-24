package com.flexplan;

public interface SaveDiscardProvider {

	void save();
	void discard();
	String getSaveDiscardMessage();
}
