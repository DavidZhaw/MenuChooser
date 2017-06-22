package ch.zhaw.iwi.alcoholtester.backend;

public class Bier extends Getraenk {
	
	public Bier() {
		super();
		amountMilli = 300;
		alcLevel = 0.05;
	}

	public void print() {
		super.print();
	}

	public static void main(String[] args) {
		Bier b = new Bier();
		Wein w = new Wein();
		Likoer l = new Likoer();
		Schnaps s = new Schnaps();

		// wait 3 seconds
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println("Fehler");
		}

		b.print();
		w.print();
		l.print();
		s.print();

		System.out.println("\nBier: " + b.getAlcTotal());
		System.out.println("Wein: " + w.getAlcTotal());
		System.out.println("Likör: " + l.getAlcTotal());
		System.out.println("Schnaps: " + s.getAlcTotal());
	}
}
