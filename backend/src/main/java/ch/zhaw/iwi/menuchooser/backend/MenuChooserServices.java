package ch.zhaw.iwi.menuchooser.backend;

import static spark.Spark.get;
import static spark.Spark.put;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ch.zhaw.iwi.menuchooser.server.json.JsonHelper;
import ch.zhaw.iwi.menuchooser.server.json.JsonTransformer;
import ch.zhaw.iwi.menuchooser.service.PathListEntry;

public class MenuChooserServices {

	private JsonTransformer jsonTransformer = new JsonHelper().getJsonTransformer();

	public void init() {
		
		String [] menues = {"Apfel", "Birne", "Tomate"};
		int [] votes = new int[menues.length];

		get("services/menus", (req, res) -> {
			return menues;
		}, jsonTransformer);

		put("services/menu/:menuNo", (req, res) -> {
			int menuNumber = Integer.parseInt(req.params("menuNo"));	
			if (menuNumber<menues.length) {
				votes[menuNumber]++;
				return true;
			}
			return false;
		}, jsonTransformer);

		get("services/menu/:menuNo", (req, res) -> {
			int menuNumber = Integer.parseInt(req.params("menuNo"));
			if (menuNumber<menues.length) {
				return votes[menuNumber];
			}
			return 0;
		}, jsonTransformer);	
	}
	
	/**
	 * @return erzeugt einen Path Button mit Namen, Detail und (möglicher) URL
	 */
	private PathListEntry<String> createButton(String name, String details, String url) {
		PathListEntry<String> button = new PathListEntry<>();
		button.setKey(UUID.randomUUID().toString(), "id"); // Jeder Path Button muss einen Key haben
		button.setName(name);
		button.getDetails().add(details);
		button.setUrl(url);
		return button;
	}

}
