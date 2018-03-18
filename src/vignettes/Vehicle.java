package vignettes;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public abstract class Vehicle {

	protected String model;
	private Vignette vignette;
	private LocalDate yearOfManufacture;
	
	public Vehicle() {
		Period days = Period.ofDays(new Random().nextInt(25)+5);
		Period months = Period.ofMonths(new Random().nextInt(11)+1);
		Period years = Period.ofYears(new Random().nextInt(18)+1);
		this.yearOfManufacture = LocalDate.now().minus(years).minus(months).minus(days);
	}
	
	public LocalDate getYearOfManufacture() {
		return yearOfManufacture;
	}
	
	public Vignette getVignette() {
		return this.vignette;
	}

	public void setVignette(Vignette vignette) {
		this.vignette = vignette;
	}
	
	public abstract int getTimeToStickVignette();
	public abstract Vignette.Color getVignetteColor();
}
