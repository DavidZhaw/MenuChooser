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
		String [] colors = {"blue","green","red","yellow","pink","purple","lime"};				
		int [] votes = new int[menus.length];
		
		
		get("services/getmenus", (req, res) -> {		
			List<PathListEntry<String>> menuButtons = new ArrayList<>();		
			for (int i=0; i<menus.length; i++) {
				String details = votes[i] + " Likes"; //+getStarString(i);
				menuButtons.add(createButton(menus[i], details, colors[i%colors.length], "/vote/"+i));
			}
			return menuButtons;
		}, jsonTransformer);
		
		
		get("services/vote/:menuNo", (req, res) -> {
			int menuNumber = Integer.parseInt(req.params("menuNo"));	
			if (menuNumber<menus.length) {
				votes[menuNumber]++;
				return true;
			}
			return false;
		}, jsonTransformer);
		
		
		get("services/admin/reset", (req, res) -> {
			for (int i=0; i<menus.length; i++) {
				votes[i] = 0;
			}
			return true;
		}, jsonTransformer);
		
		
		get("services/admin/setmenu/:menuNo/:menuName", (req, res) -> {
			int menuNumber = Integer.parseInt(req.params("menuNo"));	
			String menuName = req.params("menuName");	
			if (menuNumber<menus.length) {
				menus[menuNumber] = menuName;
				return true;
			}
			return false;
		}, jsonTransformer);
		
		get("services/menu/get/:menuNo", (req, res) -> {
			int menuNumber = Integer.parseInt(req.params("menuNo"));	
			if (menuNumber<menus.length) {
				return menus[menuNumber];
			}
			return "Empty";
		}, jsonTransformer);


		
		// Debug
		get("services/getmenunames", (req, res) -> {
			return menus;
		}, jsonTransformer);
		
		// Debug
		get("services/getvotes", (req, res) -> {
			return votes;
		}, jsonTransformer);
		
	}
	
	/**
	 * @return erzeugt einen Path Button mit Namen, Detail und (möglicher) URL
	 */
	private PathListEntry<String> createButton(String name, String details, String color, String url) {
		PathListEntry<String> button = new PathListEntry<>();
		button.setKey(UUID.randomUUID().toString(), "id"); // Jeder Path Button muss einen Key haben
		button.setName(name);
		button.setColor(color);
		button.getDetails().add(details);
		button.setUrl(url);
		return button;
	}
	
	private String getStarString(int startCount) {
		String starstring = "<br><br>";
		for (int i=0; i<5; i++) {
			if (i < startCount) {
				starstring += "&#9733;";
			} else {
				starstring += "&#9734;";
			}
		}
		return starstring;
	}
}
