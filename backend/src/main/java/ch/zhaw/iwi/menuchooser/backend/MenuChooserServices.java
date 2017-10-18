package ch.zhaw.iwi.alcoholtester.backend;

import static spark.Spark.get;
import static spark.Spark.put;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ch.zhaw.iwi.alcoholtester.server.json.JsonHelper;
import ch.zhaw.iwi.alcoholtester.server.json.JsonTransformer;
import ch.zhaw.iwi.alcoholtester.service.PathListEntry;

public class AlkServices {

	private AlkTest alkTest = new AlkTest();
	private JsonTransformer jsonTransformer = new JsonHelper().getJsonTransformer();

	public void init() {
		// GUI benötigt immer eine Person
		alkTest.setPerson(50, true);

		// Person abrufen
		get("services/person/:personId", (req, res) -> {
			return alkTest.getPerson();
		}, jsonTransformer);

		// Person aktualisieren
		put("services/person/:personId", (req, res) -> {
			Person person = new JsonHelper().fromJson(req.body(), Person.class); // Json nach Person transformieren
			alkTest.setPerson(person.getWeight(), person.isFemale());
			return true;
		}, jsonTransformer);

		// Liste der Konsumationen abrufen, gruppiert nach Typ
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

			result.add(createButton(Bier.class.getSimpleName(), bierCount + " Gläser", "/konsum/add/0"));
			result.add(createButton(Wein.class.getSimpleName(), weinCount + " Gläser", "/konsum/add/1"));
			result.add(createButton(Likoer.class.getSimpleName(), likoerCount + " Gläser", "/konsum/add/2"));
			result.add(createButton(Schnaps.class.getSimpleName(), schnapsCount + " Gläser", "/konsum/add/3"));

			return result;
		}, jsonTransformer);

		// Konsum hinzufügen
		get("services/konsum/add/:konsumId", (req, res) -> {
			Integer konsumId = Integer.parseInt(req.params("konsumId")); // Parameter nach int konvertieren
			alkTest.addKonsum(konsumId);
			return true;
		}, jsonTransformer);
		
		// aktuellen Level abrufen
		get("services/level", (req, res) -> {
			List<PathListEntry<String>> buttonList = new ArrayList<>();
			buttonList.add(createButton("Level", String.format("%.5f", alkTest.getCurrentLevel()) + " ‰", "/level"));
			return buttonList;
		}, jsonTransformer);
		
		// Liste der Konsumationen leeren
		get("services/reset", (req, res) -> {
			alkTest.getKonsum().clear();
			return true;
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
