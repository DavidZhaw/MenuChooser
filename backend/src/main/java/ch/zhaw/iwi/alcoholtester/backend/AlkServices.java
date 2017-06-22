package ch.zhaw.iwi.alcoholtester.backend;

import static spark.Spark.get;
import static spark.Spark.put;

import java.util.ArrayList;
import java.util.List;

import ch.zhaw.iwi.alcoholtester.server.json.JsonHelper;
import ch.zhaw.iwi.alcoholtester.service.PathListEntry;

public class AlkServices {

	private AlkTest alkTest = new AlkTest();

	public void init() {
		alkTest.addKonsum(0);
		alkTest.addKonsum(2);
		alkTest.addKonsum(1);
		alkTest.addKonsum(3);

		get("services/person/:personId", (req, res) -> {
			if (alkTest.hasPerson()) {
				return alkTest.getPerson();
			} else {
				return "";
			}
		}, new JsonHelper().getJsonTransformer());

		put("services/person/:personId", (req, res) -> {
			Person person = new JsonHelper().fromJson(req.body(), Person.class);
			alkTest.setPerson(person.getWeight(), person.isFemale());
			return true;
		}, new JsonHelper().getJsonTransformer());

		get("services/konsum", (req, res) -> {
			List<PathListEntry<String>> result = new ArrayList<>();

			int bierCount = 0;
			int weinCount = 0;
			int likoerCount = 0;
			int schnapsCount = 0;

			for (Getraenk getraenk : alkTest.getKonsum()) {
				if (getraenk instanceof Bier) {
					bierCount++;
				} else if (getraenk instanceof Wein) {
					weinCount++;
				} else if (getraenk instanceof Likoer) {
					likoerCount++;
				} else if (getraenk instanceof Schnaps) {
					schnapsCount++;
				}
			}

			PathListEntry<String> bier = new PathListEntry<>();
			bier.setKey(Bier.class.getSimpleName(), "konsumId");
			bier.setName(Bier.class.getSimpleName());
			bier.getDetails().add(bierCount + " Gläser");
			bier.setUrl("/konsum/add/0");
			result.add(bier);

			PathListEntry<String> wein = new PathListEntry<>();
			wein.setKey(Wein.class.getSimpleName(), "konsumId");
			wein.setName(Wein.class.getSimpleName());
			wein.getDetails().add(weinCount + " Gläser");
			wein.setUrl("/konsum/add/1");
			result.add(wein);

			PathListEntry<String> likoer = new PathListEntry<>();
			likoer.setKey(Likoer.class.getSimpleName(), "konsumId");
			likoer.setName(Likoer.class.getSimpleName());
			likoer.getDetails().add(likoerCount + " Gläser");
			likoer.setUrl("/konsum/add/2");
			result.add(likoer);

			PathListEntry<String> schnaps = new PathListEntry<>();
			schnaps.setKey(Schnaps.class.getSimpleName(), "konsumId");
			schnaps.setName(Schnaps.class.getSimpleName());
			schnaps.getDetails().add(schnapsCount + " Gläser");
			schnaps.setUrl("/konsum/add/3");
			result.add(schnaps);

			return result;
		}, new JsonHelper().getJsonTransformer());

		get("services/konsum/add/:konsumId", (req, res) -> {
			Integer konsumId = Integer.parseInt(req.params("konsumId"));
			alkTest.addKonsum(konsumId);
			return true;
		}, new JsonHelper().getJsonTransformer());
		
		get("services/level", (req, res) -> {
			List<PathListEntry<String>> result = new ArrayList<>();

			PathListEntry<String> levelButton = new PathListEntry<>();
			levelButton.setKey("1", "levelId");
			levelButton.setName("Level");
			levelButton.getDetails().add(alkTest.getCurrentLevel() + "%");
			result.add(levelButton);

			return result;
		}, new JsonHelper().getJsonTransformer());
		
		get("services/reset", (req, res) -> {
			alkTest.getKonsum().clear();
			return true;
		}, new JsonHelper().getJsonTransformer());		
	}

}
