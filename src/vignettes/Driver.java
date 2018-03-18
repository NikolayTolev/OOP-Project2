package vignettes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.management.relation.RelationNotFoundException;

import vignettes.Vignette.Type;

public class Driver {

	private static int count = 1;
	private String name;
	private ArrayList<Vehicle> vehicles;
	private double money;
	private Gasstation gasstation;

	public Driver(Gasstation gasstation) {
		if (gasstation == null) {
			throw new RuntimeException("Invalid gasstation");
		}
		this.vehicles = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			int number = new Random().nextInt(3);
			switch (number) {
			case 0: 
				this.vehicles.add(new Car()); 
				break;
			case 1:
				this.vehicles.add(new Truck());
				break;
			case 2:
				this.vehicles.add(new Bus());
				break;
			}
		}
		this.name = this.getClass().getSimpleName() + count;
		this.money = new Random().nextDouble()*400+60000;
		this.gasstation = gasstation;
		count++;
	}

	public void buyVignette(int vehicleIndex, Vignette.Type period) {
		if (vehicleIndex < 0 || vehicleIndex >= this.vehicles.size()) {
			throw new RuntimeException("Invalid vehicle index");
		}
		if (period == null) {
			throw new RuntimeException("Invalid period");
		}
		Vehicle vehicle = this.vehicles.get(vehicleIndex);
		buyVignette(vehicle, period);
	}

	public void buyVignette(Type period) {
		if (period == null) {
			throw new RuntimeException("Invalid period");
		}

		double priceForVignettes = 0;
		for (Vehicle vehicle : vehicles) {
			priceForVignettes += vehicle.getVignetteColor().price * period.multiplier;
		}
		if (this.money >= priceForVignettes) {
			for (Vehicle vehicle : vehicles) {
				buyVignette(vehicle, period);
			}
		}
	}
	
	public List<Vehicle> getVehicles() {
		return Collections.unmodifiableList(vehicles);
	}

	public void printExpiredVignettes() {

		LocalDate today = LocalDate.now();
		System.out.println(this.name + "'s vehicles with expired vignettes:" + "\n");
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getVignette().getExpiryDate().isBefore(today)) {
				vehicle.toString();
			}
		}
	}

	private void buyVignette(Vehicle vehicle, Type period) {

		if (this.money >= (vehicle.getVignetteColor().price * period.multiplier)) {
			Vignette vignette = this.gasstation.sellVignette(vehicle, period);
			this.money -= vignette.getPrice();
			vignette.stickOnWindow(vehicle);
		} else {
			throw new RuntimeException("Not enough money");
		}
	}
	
	public void printInfo(LocalDate date) {
		
		int vehiclesCount = this.vehicles.size();
		long countOfExpiredVign = this.vehicles.stream().filter( (Vehicle v1) -> v1.getVignette() == null || v1.getVignette().getExpiryDate().isBefore(date)).count();
		System.out.printf("%s : cars: %d ; with expired or without vignettes: %d ; money left: %.2f LV%n", this.name, vehiclesCount, countOfExpiredVign, this.money); 
	}
}
