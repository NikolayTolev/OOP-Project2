package vignettes;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Demo {

	public static void main(String[] args) {

		String result = new Gasstation().printVignettes();
		System.out.print(result);

		Gasstation gasstation = new Gasstation();

		List<Driver> drivers = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			drivers.add(new Driver(gasstation));
		}

		for (int i = 0; i < drivers.size(); i++) {
			Driver d = drivers.get(i);
			Vignette.Type period = Vignette.Type.values()[new Random().nextInt(Vignette.Type.values().length)];
			if (i % 3 == 2) {
				for (int j = 0; j < 5; j++) {
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
