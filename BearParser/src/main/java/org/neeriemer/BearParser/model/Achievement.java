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
}
