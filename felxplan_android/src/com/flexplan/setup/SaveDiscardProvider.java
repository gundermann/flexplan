package com.flexplan.setup;

public interface SaveDiscardProvider {

	void save();
	void discard();
	String getSaveDiscardMessage();
}
