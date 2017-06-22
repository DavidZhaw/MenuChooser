package ch.zhaw.iwi.alcoholtester.backend;

public class Getraenk {

	private long drinkTime;
	public int amountMilli;
	public double alcLevel;

	public Getraenk() {
		drinkTime = System.currentTimeMillis();
	}

	public long getDiffSecond() {
		return (System.currentTimeMillis() - drinkTime) / 1000;
	}

	public long getTimeMilli() {
		return drinkTime;
	}

	public double getAlcTotal() {
		return amountMilli * alcLevel * 0.8;
	}

	public void print() {

		System.out.println("Getränke:  "+this.getClass().getName());
		System.out.println("Konsum vor "+getDiffSecond()+" Sekunden");
		System.out.println("Menge:     "+amountMilli+" Milliliter");
		System.out.println("Gehalt:    "+alcLevel);
	}

	public static void main(String[] args) {
		Getraenk g = new Getraenk();
		g.amountMilli = 300;
		g.alcLevel = 0.05;

		// wait 2 seconds
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println("Fehler");
		}

		double alk = g.getAlcTotal();
		long time = g.getTimeMilli();
		long delta = g.getDiffSecond();

		System.out.println(alk + " g, " + time + " ms, " + delta + " sec");
		System.out.println("\nAusgabe von print()");
		g.print();
	}
}
