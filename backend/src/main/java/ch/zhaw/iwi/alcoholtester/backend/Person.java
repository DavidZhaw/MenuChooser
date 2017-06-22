package ch.zhaw.iwi.alcoholtester.backend;

public class Person {
	
	private boolean isFemale;
	private double weight;

	public Person(double weight, boolean isFemale) {
		this.weight = weight;
		this.isFemale = isFemale;
	}

	public double getDistribution() {
		if (isFemale) {
			return weight * 0.6;
		} else {
			return weight * 0.7;
		}
	}
	
	public boolean isFemale() {
		return isFemale;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void print(){
		System.out.println("Geschlecht:\t" + (isFemale ? "Frau" : "Mann"));
		System.out.println("Gewicht:\t"+ weight);
		System.out.println("Reduktion:\t"+ getDistribution());
	}

	public static void main(String[] args) {
		Person mann = new Person(80, false);
		Person frau = new Person(60, true);

		System.out.println("R1: " + mann.getDistribution());
		System.out.println("R2: " + frau.getDistribution());

		mann.print();
		frau.print();
	}
}
