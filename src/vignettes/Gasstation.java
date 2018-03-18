package vignettes;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Gasstation {

	private double dayBalance;
	private TreeSet<Vignette> vignettes;

	public Gasstation() {
		System.out.println("New gasstation opened.");
		this.vignettes = new TreeSet<>();
		generateVignettes();
	}

	private void generateVignettes() {

		for (int i = 0; i < 10000; i++) {
			vignettes.add(new Vignette());
		}
	}

	public Set<Vignette> getVignettes() {
		return Collections.unmodifiableSet(this.vignettes);
	}

	public Vignette sellVignette(Vehicle vehicle, Vignette.Type period) {

		for (Vignette vignette : vignettes) {
			if (vignette.getTypeOfVignette() == period
					&& vehicle.getVignetteColor() == vignette.getColor()) {
				this.vignettes.remove(vignette);
				this.dayBalance += vignette.getPrice();
				return vignette;
			}
		}
		return null;
	}

	public String printVignettes() {
		StringBuilder builder = new StringBuilder();
		for (Vignette vignette : vignettes) {
			builder.append(vignette.toString() + "\n");
		}
		return builder.toString();
	}
}
