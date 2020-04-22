package com.campuslandes.ecampus.web.rest.utils;

import java.util.Random;

public class randomUtils {

    public static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

}
