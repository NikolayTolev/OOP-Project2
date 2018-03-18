package test;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import vignettes.Driver;
import vignettes.Gasstation;
import vignettes.Vehicle;
import vignettes.Vignette;

class GasStationTest {

	@Test
	void test_generate10000Vignettes() {
		String result = new Gasstation().printVignettes();
		System.out.print(result);
		String[] lines = result.split("\n");
		if (lines.length != 10000) {
			throw new RuntimeException("Expected 10000 but got " + lines.length + " lines");
		}
	}
	
	@Test
	void test_createDriversGenerate10Vehicles() {
		Gasstation gasstation = new Gasstation();
		
		List<Driver> drivers = new ArrayList<>();
		for (int i =0; i < 20; i++) {
			drivers.add(new Driver(gasstation));
			if (drivers.get(i).getVehicles().size() != 10) {
				throw new RuntimeException("Unexpected vehicle count, 10 should be generated but were " + drivers.get(i).getVehicles().size());
			}
		}
	
	}
	
	@Test
	void test_buyVignettes() {
		Gasstation gasstation = new Gasstation();
		
		List<Driver> drivers = new ArrayList<>();
		for (int i =0; i < 20; i++) {
			drivers.add(new Driver(gasstation));
		}
		
		for (int i =0; i < drivers.size(); i++) {
			Driver d = drivers.get(i);
			Vignette.Type period = Vignette.Type.values()[new Random().nextInt(Vignette.Type.values().length)];
			if (i % 3 == 2) {
				for (int j =0; j<5; j++) {
					d.buyVignette(new Random().nextInt(d.getVehicles().size()), period);
				}
			} else {
				d.buyVignette(period);
			}
			LocalDate date = LocalDate.now().plus(Period.ofMonths(6));
			d.printInfo(date);
		}
	}

	
}

