package com.flexplan.util;

public class NoAnnotationFoundException extends Exception {

	public NoAnnotationFoundException(String string) {
		super("Lost Annotation: " + string);
	}

	private static final long serialVersionUID = 2461646504365900909L;

}
