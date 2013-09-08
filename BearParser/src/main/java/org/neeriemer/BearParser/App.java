package org.neeriemer.BearParser;

import java.io.File;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.trello4j.Trello;
import org.trello4j.TrelloImpl;
import org.trello4j.model.Board;
import org.trello4j.model.Checklist;

public class App {

	private static final String TRELLO_KEY = "d2d8c722a7238bafb3cb18c1c07bddb8";
	private static final String TRELLO_TOKEN = "5752bca0ba15fc3c9f5c87329a4487cd79f8bb815350f2cc498e1b2afb6c9966";

	private static final String DEN_ORG_NAME = "den41";
	
	private static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	public static void main(String[] args) {
		System.out.println("Starting...");
		try {
			Document doc = Jsoup.parse(new File("req.html"), null);
			Elements categories = doc.select("b.category");
			Elements  lists = doc.select("body > ol");
			
			int section = 1;
			for (int i = 0; i < categories.size(); i++) {
				Element category = categories.get(i);
				System.out.println(category.text());
				Element list = lists.get(i);
				for (Element item : list.children()) {
					Element title = item.child(0);
					Element reqs = item.child(1);
					System.out.println("\t" + section++ + ". " + title.text());
					System.out.println("\t\t" + item.ownText());
					int id = 0;
					for (Element requirement: reqs.children()) {
						System.out.println("\t\t\t" + alphabet[id++] + ". " + requirement.text());
						
					}
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done.");
	}

	private static void trello() {
		try {
			Trello trello = new TrelloImpl(TRELLO_KEY, TRELLO_TOKEN);

			List<Board> boards = trello.getBoardsByOrganization(DEN_ORG_NAME);

			for (Board b : boards) {
				if (!b.isClosed()) {
					listChecklists(trello, b);
					System.out.println(b.getName());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void listChecklists(Trello trello, Board b) {

		List<Checklist> lists = trello.getChecklistByBoard(b.getId());
		for (Checklist l : lists) {
			System.out.println(l.getName());
		}
	}

}
