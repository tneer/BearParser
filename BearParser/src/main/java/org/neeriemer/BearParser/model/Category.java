package org.neeriemer.BearParser.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
	
	private final String name;
	private final String description;
	private final List<Achievement> achievements;
	
	public Category(final String name, final String description) {
		this.name = name;
		this.description = description;
		achievements = new ArrayList<Achievement>();
	}
	
	public void addAchievement(final Achievement achievement) {
		achievements.add(achievement);
	}

}
