package ulaval.glo2003.floppa.app.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DateUtilTest {

	@Test
	void givenLocalDateTime_whenToISO8601WithHours_thenCorrectFormat() {
		int year = 2000;
		int month = 10;
		int day = 21;
		int hour = 12;
		int minutes = 42;
		int seconds = 54;
		LocalDateTime now = LocalDateTime.of(year, month, day, hour, minutes, seconds);

		String realFormat = DateUtil.toISO8601WithHours(now);

		Assertions.assertEquals(String.format("%s-%s-%sT%s:%s:%sZ", year, month, day, hour, minutes, seconds), realFormat);
	}

	@Test
	void givenLocalDate_whenToISO8601WithNoHours_thenCorrectFormat() {
		int year = 2000;
		int month = 10;
		int day = 21;
		LocalDate now = LocalDate.of(year, month, day);

		String realFormat = DateUtil.toISO8601WithNoHours(now);

		Assertions.assertEquals(String.format("%s-%s-%s", year, month, day), realFormat);
	}
}