package org.neeriemer.BearParser.model;

public class Requirement {

	private final String id;
	private final String text;

	private final Achievement achievement;

	public Requirement(final Achievement achievement, final String id, final String text) {
		this.id = id;
		this.text = text;
		this.achievement = achievement;
		this.achievement.addRequirement(this);
	}
	public String getId() {
		return id;
	}
	
	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return "\t\t\t" + id + ". " + text;
	}

}
