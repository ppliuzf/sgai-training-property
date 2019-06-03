package com.sgai.property.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 描述:日期工具类.
 *
 * @author ppliu
 * created in 2019/5/30 11:00
 */
public class DateUtil {
    /**
     * 获取包含当天的过去n天.
     */
    public static List<LocalDate> getRecentDay(LocalDate date, int days) {
        LocalDate start = date.plusDays(-days + 1);
        return Stream.iterate(start, localDate -> localDate.plusDays(1))
                .limit(ChronoUnit.DAYS.between(start, date) + 1)
                .collect(Collectors.toList());
    }

    /**
     * 获取包含此小时的过去n小时.
     */
    public static List<LocalDateTime> getRecentTime(LocalDateTime time, int hours) {
        LocalDateTime start = time.plusHours(-hours + 1);
        return Stream.iterate(start, localDateTime -> localDateTime.plusHours(1))
                .limit(ChronoUnit.HOURS.between(start, time) + 1)
                .collect(Collectors.toList());
    }
}
