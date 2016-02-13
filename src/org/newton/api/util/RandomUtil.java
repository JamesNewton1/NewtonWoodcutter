package org.newton.api.util;

import java.util.Random;

public class RandomUtil {

	private static Random rng = new Random();
	
	public static int randomGaussian(int min, int max, int mean, int stdDeviation) throws IllegalArgumentException {
		if(max <= min) throw new IllegalArgumentException("Max cannot be less than or equal to min!");
		int result = Integer.MIN_VALUE;
		
		while(result < min || result > max) {
			result = (int) (mean + (rng.nextGaussian() * stdDeviation));
		}
		
		return result;
	}
	
}
