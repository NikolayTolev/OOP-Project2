package vignettes;

import java.util.Random;

import vignettes.Vignette.Color;

public class Truck extends Vehicle {

	private static final String[] MODELS = {"Scania","Volvo","Kenworth"};

	public Truck() {
		this.model = MODELS[new Random().nextInt(MODELS.length)];
	}

	@Override
	public int getTimeToStickVignette() {
		return 10;
	}

	@Override
	public Color getVignetteColor() {
		return Color.BLUE;
	}
}
