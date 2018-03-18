package vignettes;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public class Vignette implements Comparable<Vignette>{
	
	public enum Type{
		
		DAY(1, Period.ofDays(1)), MONTH(10, Period.ofMonths(1)), YEAR(60, Period.ofYears(1));
		
		int multiplier;
		Period validity;
		private Type(int multiplier, Period validity) {
			this.multiplier = multiplier;
			this.validity = validity;
		}
	}
	
	public enum Color{
		
		RED(9), GREEN(5), BLUE(7);
		
		int price;
		private Color(int price) {
			this.price = price;
		}
	}

	private LocalDate dateOfIssue;
	private Color color;
	private Type typeOfVignette;
	private LocalDate expiryDate;
	
	public Vignette() {
		this.dateOfIssue = LocalDate.now();
		this.color = Color.values()[new Random().nextInt(Color.values().length)];
		this.typeOfVignette = Type.values()[new Random().nextInt(Type.values().length)];
		this.expiryDate = this.dateOfIssue.plus(this.typeOfVignette.validity);
	}
	
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	
	public Type getTypeOfVignette() {
		return typeOfVignette;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int stickOnWindow(Vehicle vehicle) {
		vehicle.setVignette(this);
		
		return vehicle.getTimeToStickVignette();
	}
	
	public double getPrice() {
		return this.color.price * this.typeOfVignette.multiplier;
	}

	@Override
	public int compareTo(Vignette o) {
		if (this.getPrice() == o.getPrice()) {
			return 1;
		}
		return Double.compare(this.getPrice(), o.getPrice());
	}
	
	@Override
	public String toString() {
		return String.format("%s vignette -> color: %s ; price: %f", this.typeOfVignette, this.color, this.getPrice());
	}
}
