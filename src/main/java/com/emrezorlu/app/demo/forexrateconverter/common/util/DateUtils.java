package com.emrezorlu.app.demo.forexrateconverter.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.emrezorlu.app.demo.forexrateconverter.common.enums.ErrorCode;
import com.emrezorlu.app.demo.forexrateconverter.common.exception.BusinessException;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class DateUtils {
	private static final ZoneId zone = ZoneId.of("GMT+3");

	public static LocalDateTime convertToLocalDateTime(Long timestamp) {
		return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), zone);
	}

	public static Date truncDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}

	public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(zone).toLocalDateTime();
	}

	public static String convertToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		dateFormat.setTimeZone(TimeZone.getTimeZone(zone));
		return dateFormat.format(date);
	}

	public static Date convertToDate(String strDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		dateFormat.setTimeZone(TimeZone.getTimeZone(zone));
		Date parsedDate = null;
		try {
			parsedDate = dateFormat.parse(strDate);
		} catch (ParseException e) {
			log.error(e.getMessage());
			throw new BusinessException(strDate + " cannot be parsed yyyy-MM-dd'T'HH:mm:ss.SSS",
					ErrorCode.DATE_PARSE_ERROR);
		}
		return parsedDate;

	}

	public static Date shortDateFormatForQuery(Date date) {
		return convertToDate(convertToString(date));
	}
}
