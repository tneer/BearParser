package org.neeriemer.BearParser.model;

import java.util.ArrayList;
import java.util.List;

public class Achievement {

	final int id;
	final String title;
	final String instructions;
	final List<Requirement> requirements;
	final Category category;

	public Achievement(final Category category, final int id, final String title,
			final String instructions) {
		this.category = category;
		this.id = id;
		this.title = title;
		this.instructions = instructions;
		requirements = new ArrayList<Requirement>();
		this.category.addAchievement(this);
	}

	public void addRequirement(final Requirement req) {
		requirements.add(req);
	}

	public List<Requirement> getRequirements() {
		return requirements;
	}
	
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getInstructions() {
		return instructions;
	}

	@Override
	public String toString() {
		return String.format("\t%1$d. %2$s\n\t\t%3$s", id, title, instructions);
	}
}
