package org.neeriemer.BearParser.model;

public class Requirement {

	private final String id;
	private final String text;
	
	public Requirement(final String id, final String text) {
		this.id = id;
		this.text = text;
	}

	@Override
	public String toString() {
		return id + ". " + text;
	}



}
