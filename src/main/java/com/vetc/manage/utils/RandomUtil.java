package com.vetc.manage.utils;

import java.security.SecureRandom;
import java.util.Random;
/** 
 * @Author HungVM
 */
public class RandomUtil {
	private static char[] VALID_ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private static char[] VALID_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456879"
			.toCharArray();
	private static char[] VALID_CHARACTERS_ALPHA = "0123456879".toCharArray();

	// cs = cryptographically secure
	public static String csRandomAlphaNumericString(int numChars) {
		SecureRandom srand = new SecureRandom();
		Random rand = new Random();
		char[] buff = new char[numChars];

		for (int i = 0; i < numChars; ++i) {
			// reseed rand once you've used up all available entropy bits
			if ((i % 10) == 0) {
				rand.setSeed(srand.nextLong()); // 64 bits of random!
			}
			buff[i] = VALID_CHARACTERS[rand.nextInt(VALID_CHARACTERS.length)];
		}
		return new String(buff);
	}

	public static String csRandomAlphaString(int numChars) {
		SecureRandom srand = new SecureRandom();
		Random rand = new Random();
		char[] buff = new char[numChars];

		for (int i = 0; i < numChars; ++i) {
			// reseed rand once you've used up all available entropy bits
			if ((i % 10) == 0) {
				rand.setSeed(srand.nextLong()); // 64 bits of random!
			}
			buff[i] = VALID_ALPHA[rand.nextInt(VALID_ALPHA.length)];
		}
		return new String(buff);
	}

	public static String csRandomNumericString(int numChars) {
		SecureRandom srand = new SecureRandom();
		Random rand = new Random();
		char[] buff = new char[numChars];

		for (int i = 0; i < numChars; ++i) {
			// reseed rand once you've used up all available entropy bits
			if ((i % 10) == 0) {
				rand.setSeed(srand.nextLong()); // 64 bits of random!
			}
			buff[i] = VALID_CHARACTERS_ALPHA[rand.nextInt(VALID_CHARACTERS_ALPHA.length)];
		}
		return new String(buff);
	}

	public static String csRandomNumericStringOtpDto(int numChars) {
		SecureRandom srand = new SecureRandom();
		Random rand = new Random();
		char[] buff = new char[numChars];

		for (int i = 0; i < numChars; ++i) {
			// reseed rand once you've used up all available entropy bits
			if ((i % 10) == 0) {
				rand.setSeed(srand.nextLong()); // 64 bits of random!
			}
			buff[i] = VALID_CHARACTERS_ALPHA[rand.nextInt(VALID_CHARACTERS_ALPHA.length)];
		}
		return new String(buff);
	}

}
