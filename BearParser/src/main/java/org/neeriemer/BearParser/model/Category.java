package org.neeriemer.BearParser.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Category {

	private final String name;
	private final String description;
	private final List<Achievement> achievements;

	private static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	public static List<Category> buildCategoryList(File htmlDoc)
			throws IOException {
		List<Category> categoryList = new ArrayList<Category>();
		Document doc = Jsoup.parse(htmlDoc, null);
		Elements categories = doc.select("b.category");
		Elements lists = doc.select("body > ol");

		int section = 1;
		for (int i = 0; i < categories.size(); i++) {
			Element category = categories.get(i);
			Category cat = new Category(category);
			Element list = lists.get(i);
			for (Element item : list.children()) {
				Element title = item.child(0);
				Element reqs = item.child(1);
				Achievement ach = new Achievement(cat, section++, title.text(),
						item.ownText());
				int id = 0;
				for (Element requirement : reqs.children()) {
					new Requirement(ach, new String(
							new char[] { alphabet[id++] }), requirement.text());

				}
			}
			categoryList.add(cat);
		}

		return categoryList;
	}

	public Category(final String name, final String description) {
		this.name = name;
		this.description = description;
		achievements = new ArrayList<Achievement>();
	}

	public Category(Element category) {
		Pattern p = Pattern.compile("(\\w+)\\s\\((.+)\\)");
		Matcher m = p.matcher(category.text());
		if (m.matches()) {
			this.name = m.group(1);
			this.description = m.group(2);
		} else {
			this.name = category.text();
			this.description = null;
		}

		achievements = new ArrayList<Achievement>();

	}

	public void addAchievement(final Achievement achievement) {
		achievements.add(achievement);
	}

	public List<Achievement> getAchievements() {
		return achievements;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return String.format("%1$s (%2$s)", name, description);
	}

}
