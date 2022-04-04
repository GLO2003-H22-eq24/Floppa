package ulaval.glo2003.floppa.app.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	private static final DateTimeFormatter formatISO8601WithHours = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:'Z'");
	private static final DateTimeFormatter formatISO8601WithNoHours = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private DateUtil() { //only util func
	}

	public static LocalDateTime getUtcNow(){
		return OffsetDateTime.now(ZoneOffset.UTC).toLocalDateTime();
	}

	public static String toISO8601WithHours(LocalDateTime localDateTime) {
		return localDateTime.format(formatISO8601WithHours);
	}


	public static String toISO8601WithNoHours(LocalDate localDate) {
		return localDate.format(formatISO8601WithNoHours);
	}
}
