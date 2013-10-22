package org.neeriemer.BearParser;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class App {

	private static final String TRELLO_KEY = "d2d8c722a7238bafb3cb18c1c07bddb8";
	private static final String TRELLO_TOKEN = "c529cf50dddc74f01a36b1898dc98a1530bb1020bd1e10eb4dbd82e893b56b0c";

	private static final String DEN_ORG_NAME = "beartest";

	public static void main(String[] args) {
		System.out.println("Starting...");
		try {
			// List<Category> categories = Category.buildCategoryList(new File(
			// "req.html"));
			// for (Category cat : categories) {
			// System.out.println(cat);
			// for (Achievement ach : cat.getAchievements()) {
			// System.out.println(ach);
			// for (Requirement req : ach.getRequirements()) {
			// System.out.println(req);
			// }
			// }
			// }

			trello("James");
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done.");
	}

	private static void trello(final String boardName) {

		JsonObject model = Json.createObjectBuilder().add("name", boardName)
				.add("idOrganization", DEN_ORG_NAME).build();

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(buildUrl("boards"));

		Response postResponse = target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(model.toString(),
						MediaType.APPLICATION_JSON));

		System.out.println(postResponse);
	}

	private static String buildUrl(final String typeName) {
		StringBuilder sb = new StringBuilder();
		sb.append("https://api.trello.com/1/").append(typeName + "?")
				.append("key=" + TRELLO_KEY).append("&token=" + TRELLO_TOKEN);

		return sb.toString();
	}

}
