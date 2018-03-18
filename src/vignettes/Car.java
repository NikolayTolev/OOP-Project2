package vignettes;

import java.util.Random;

import vignettes.Vignette.Color;

public class Car extends Vehicle{

	private static final String[] MODELS = {"Audi","BMW","VW","Mercedes","Opel","Volvo","Peugeot"};
	
	public Car() {
		this.model = MODELS[new Random().nextInt(MODELS.length)];
	}

	@Override
	public int getTimeToStickVignette() {
		
		return 5;
	}

	@Override
	public Color getVignetteColor() {
		return Color.GREEN;
	}
}
