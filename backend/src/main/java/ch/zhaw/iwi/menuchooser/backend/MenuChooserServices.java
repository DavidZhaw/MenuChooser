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
		
		String [] menus = {"Apfel", "Birne", "Tomate"};
		int [] votes = new int[menus.length];
		
		
		get("services/getmenus", (req, res) -> {		
			List<PathListEntry<String>> menuButtons = new ArrayList<>();
			for (int i=0; i<menus.length; i++) {
				menuButtons.add(createButton(menus[i], votes[i] + " Likes", "/setvote/"+i));
			}
			return menuButtons;
		}, jsonTransformer);
		

		get("services/getmenunames", (req, res) -> {
			return menus;
		}, jsonTransformer);
		
		get("services/getvotes", (req, res) -> {
			return votes;
		}, jsonTransformer);
		
		get("services/setmenuname/:menuNo/:menuName", (req, res) -> {
			int menuNumber = Integer.parseInt(req.params("menuNo"));	
			String menuName = req.params("menuName");	
			if (menuNumber<menus.length) {
				menus[menuNumber] = menuName;
				return true;
			}
			return false;
		}, jsonTransformer);
		
		get("services/getmenuname/:menuNo", (req, res) -> {
			int menuNumber = Integer.parseInt(req.params("menuNo"));	
			if (menuNumber<menus.length) {
				return menus[menuNumber];
			}
			return "Empty";
		}, jsonTransformer);

		get("services/setvote/:menuNo", (req, res) -> {
			int menuNumber = Integer.parseInt(req.params("menuNo"));	
			if (menuNumber<menus.length) {
				votes[menuNumber]++;
				

				return true;
			}
			return false;
		}, jsonTransformer);

		get("services/getvote/:menuNo", (req, res) -> {
			int menuNumber = Integer.parseInt(req.params("menuNo"));
			if (menuNumber<menus.length) {
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
