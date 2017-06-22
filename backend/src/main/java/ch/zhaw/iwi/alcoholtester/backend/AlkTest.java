package ch.zhaw.iwi.alcoholtester.backend;

import java.util.ArrayList;

public class AlkTest {

	public int secondsOfHour = 3600; // in sek., normalerweise 3600
	private Person person;
	private ArrayList<Getraenk> konsum = new ArrayList<>();

	public void setPerson(double weight, boolean isFemale) {
		person = new Person(weight, isFemale);
	}

	public boolean hasPerson() {
		return person != null;
	}

	public void printPerson() {
		person.print();
	}

	public void addKonsum(int drink) {
		Getraenk g = null;
		switch (drink) {
		case 0:
			g = new Bier();
			break;
		case 1:
			g = new Wein();
			break;
		case 2:
			g = new Likoer();
			break;
		case 3:
			g = new Schnaps();
			break;
		default:
			break;
		}

		if (g != null) {
			konsum.add(g);
		}
	}

	public void printAll() {

		System.out.println();
		printPerson();
		for (Getraenk g : konsum) {
			System.out.println();
			g.print();
		}

	}
	
	public ArrayList<Getraenk> getKonsum() {
		return konsum;
	}
	
	public Person getPerson() {
		return person;
	}
	
	// die Hauptmethode des ganzen Projektes, 
	// berechnet aktuellen Blutalkoholgehalt
	public double getCurrentLevel() {
		double level = 0;
		
		for (int i=0; i < konsum.size(); i++) {
			Getraenk drink = konsum.get(i);
			// Alkohol des neuen Getr�nks zu Blutalkoholgehalt addieren
			level += drink.getAlcTotal() / person.getDistribution();

			// Abbau seit letztem Getr�nk berechnen und subtrahieren
			double abbaurateProSekunde = 0.15d / secondsOfHour;
			if (i != konsum.size() - 1) {
				// alle Getr�nke ausser letztes Getr�nk
				Getraenk nextDrink = konsum.get(i + 1);
				// Zeit zwischen Getr�nken berechnen
				double deltaSec = (nextDrink.getTimeMilli() - drink.getTimeMilli()) / 1000d;
				level -= abbaurateProSekunde * deltaSec;
			} else {
				// letztes Getr�nk, vergangene Zeit berechnen
				double deltaSec = drink.getDiffSecond();
				level -= abbaurateProSekunde * deltaSec;
			}

			if (level < 0) {
				level = 0;
			}
		}

		return level;
	}

}
