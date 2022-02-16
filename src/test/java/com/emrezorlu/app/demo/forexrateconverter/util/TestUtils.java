package com.emrezorlu.app.demo.forexrateconverter.util;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public final class TestUtils {
	private static EasyRandom nonRandomgenerator;

	public static EasyRandom getNonRandomgenerator() {
		if (nonRandomgenerator == null) {
			EasyRandomParameters param = new EasyRandomParameters();
			param.setCollectionSizeRange(new EasyRandomParameters.Range<Integer>(1, 3));
			param.randomize(Long.class, () -> 1L);
			param.randomize(Integer.class, () -> 1);
			param.randomize(String.class, () -> "A");
			param.randomize(Boolean.class, () -> true);
			param.randomize(Date.class, () -> Date.from(Instant.ofEpochMilli(1000)));
			param.randomize(Double.class, () -> Double.valueOf(1));
			param.randomize(BigDecimal.class, () -> BigDecimal.valueOf(10));
			param.randomize(LocalDateTime.class,
					() -> LocalDateTime.ofInstant(Instant.ofEpochSecond(1644777362), ZoneId.of("GMT+3")));
			param.ignoreRandomizationErrors(true);
			nonRandomgenerator = new EasyRandom(param);
		}
		return nonRandomgenerator;
	}

	public static <T> List<T> nextList(final Class<T> type, int maxCount) {
		int randomCount = maxCount < 2 ? 1 : maxCount;
		List<T> resultList = new ArrayList<T>();
		for (int i = 0; i < randomCount; i++) {
			resultList.add(getNonRandomgenerator().nextObject(type));
		}
		return resultList;
	}

	public static <T> List<T> listOf(Class<T> targetClass) {
		return Collections.singletonList(targetClass.cast(getNonRandomgenerator().nextObject(targetClass)));
	}

	public static <T> T objectOf(Class<T> targetClass) {
		return targetClass.cast(getNonRandomgenerator().nextObject(targetClass));
	}

	public static <K, T> Map<K, T> mapOf(K key, Class<T> targetClass) {
		return Collections.singletonMap(key, targetClass.cast(getNonRandomgenerator().nextObject(targetClass)));
	}

}
