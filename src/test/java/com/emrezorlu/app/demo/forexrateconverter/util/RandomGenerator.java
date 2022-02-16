package com.emrezorlu.app.demo.forexrateconverter.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.github.javafaker.Faker;

public class RandomGenerator {

	private final static Faker faker = new Faker();

	public static String randomId() {
		return faker.lorem().characters(5, 10, true);
	}

	public static String randomCurrency() {
		return faker.currency().code();
	}

	public static String randomString() {
		return faker.witcher().character();
	}

	public static int randomInt() {
		return faker.number().randomDigitNotZero();
	}

	public static BigDecimal randomBigdecimal() {
		return new BigDecimal(randomInt());
	}

	public static <T> List<T> randomList(Supplier<T> randomGeneratorFunc, int size) {
		return IntStream.rangeClosed(1, size).boxed().map(i -> randomGeneratorFunc.get()).collect(Collectors.toList());
	}

}
