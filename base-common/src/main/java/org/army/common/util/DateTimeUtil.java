package org.army.common.util;

import org.army.base.common.to.Range;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateTimeUtil {

    public static Range<Date> getStartAndEnd(Date date) {

        Range<Date> startAndEnd = new Range<>();

        LocalDateTime localDateTime = LocalDateTime
                .ofInstant(date.toInstant(), ZoneId.systemDefault())
                .toLocalDate()
                .atStartOfDay();

        Date start = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Date end = Date.from(localDateTime
                .plus(1, ChronoUnit.DAYS)
                .minus(1, ChronoUnit.SECONDS)
                .atZone(ZoneId.systemDefault())
                .toInstant());

        startAndEnd.setFrom(start);
        startAndEnd.setTo(end);

        return startAndEnd;
    }

}
