package vignettes;

import java.util.Random;

import vignettes.Vignette.Color;

public class Bus extends Vehicle {

	private static final String[] MODELS = { "Mercedes-Benz","Fiat","Irizar","Plaxton","Isuzu","MCV"};

	public Bus() {
		this.model = MODELS[new Random().nextInt(MODELS.length)];
	}

	@Override
	public int getTimeToStickVignette() {
		return 20;
	}

	@Override
	public Color getVignetteColor() {
		return Color.RED;
	}
}
