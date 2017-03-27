package com.ibingbo.test;

import org.joda.time.Chronology;
import org.junit.Test;

import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.JapaneseChronology;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by bing on 2017/3/10.
 */
public class LocalDateTimeTest {

    @Test
    public void testTime() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //当前时间
        LocalTime time=LocalTime.now();
        //当前日期时间
        LocalDateTime date=LocalDateTime.now();


        System.out.println("--------"+date.format(formatter1));
        //格式化日期或时间为字符串
        String d = date.format(formatter);
        System.out.println("格式化时间：" + d);
        //2017-03-10T16:30:19.528
        System.out.println("格式化时间1：" +DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now()));
        //2017-3-10 16:33:12
        System.out.println("格式化时间2：" +DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(LocalDateTime.now()));
        //解析时间字符串为LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(d, formatter);
        System.out.println(dateTime);

        //Instant表示某一个时间点的时间戳，可以类比于java.uti.Date
        Instant instant=Instant.now();
        //当前时间秒数，即时间缀
        System.out.println(instant.getEpochSecond());
        System.out.println(instant.getNano());

        //LocalDateTime转Date
        Date date1 = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(date1);
        System.out.println(ZoneId.systemDefault());

        //Date转LocalDateTime
        Date date2 = new Date();
        LocalDateTime dateTime1 = LocalDateTime.ofInstant(date2.toInstant(), ZoneId.systemDefault());
        System.out.println(formatter.format(dateTime1));

        //获取当前日期
        System.out.println(LocalDate.now().format(formatter1));
        //获取当前日期的前一天
        System.out.println(LocalDate.now().minusDays(1).format(formatter1));
        //获取当前日期的前一月
        System.out.println(LocalDate.now().minusMonths(1).format(formatter1));
        //获取当前日期的前一周
        System.out.println(LocalDate.now().minusWeeks(1).format(formatter1));

        //当前日期时间
        System.out.println(LocalDateTime.now().format(formatter));
        //当前日期时间的前一天
        System.out.println(LocalDateTime.now().minusDays(1).format(formatter));
        //当前日期时间的前一月
        System.out.println(LocalDateTime.now().minusMonths(1).format(formatter));
        //当前日期时间的前一周
        System.out.println(LocalDateTime.now().minusWeeks(1).format(formatter));
        //当前日期时间的前一小时
        System.out.println(LocalDateTime.now().minusHours(1).format(formatter));
        //当前日期时间的前一秒
        System.out.println(LocalDateTime.now().minusSeconds(1).format(formatter));

        //获取指定时间的日期
        LocalDateTime midnight = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        System.out.println(midnight.format(formatter));

        //获取当天午夜00-00-00的日期时间
        LocalTime midTime = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now();
        LocalDateTime todayMidnight = LocalDateTime.of(today, midTime);
        System.out.println(todayMidnight);
        //等价于
        LocalDateTime todayMidnight1 = today.atTime(midTime);
        System.out.println(todayMidnight1);
        //等价于
        LocalDateTime atStartOfDay = LocalDate.now().atStartOfDay();
        System.out.println("一天的开始：" + atStartOfDay.format(formatter));

        //明天午夜00-00-00的日期时间
        LocalDateTime tomorrowMidnight = todayMidnight.plusDays(1);
        System.out.println(tomorrowMidnight);


        //Duration表示Instant之间的时间差，可以用来统计任务的执行时间，也支持各种运算操作
        //计算两个时间相差的秒数
        long secs = ChronoUnit.SECONDS.between(midnight, LocalDateTime.now());
        System.out.println(secs);
        long nanos = Duration.between(midnight, LocalDateTime.now()).toNanos();
        long secs1 = TimeUnit.NANOSECONDS.toSeconds(nanos);
        System.out.println(secs1);

        //计算两个时间的相差的分钟数
        long minits = ChronoUnit.MINUTES.between(midnight, LocalDateTime.now());
        System.out.println(minits);
        long minits1 = Duration.between(midnight, LocalDateTime.now()).toMinutes();
        System.out.println(minits1);

        //计算两个时间的相差的小时数
        long hours = ChronoUnit.HOURS.between(midnight, LocalDateTime.now());
        System.out.println(hours);
        long hours1 = Duration.between(midnight,LocalDateTime.now()).toHours();
        System.out.println(hours1);

        //Period用来表示两个LocalDate之间的时间差
        LocalDate today1 = LocalDate.now();
        LocalDate yesterday = LocalDate.of(today1.getYear(), today1.getMonth(), today1.getDayOfMonth() - 1);
        Period period = yesterday.until(today1);
        System.out.println("period: " + period);
        //等价于
        period = Period.between(yesterday, today1);
        System.out.println("period: " + period);
        System.out.println(period.isNegative());
        System.out.println(period.getDays());


        //TemporalAdjusters时间调节器，用于表示某个月第一天、下个周一等日期
        LocalDate firstDay = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("每月的第一天：" + firstDay);
        LocalDate lastDay = LocalDate.now().minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("上个月的第一天：" + lastDay);
        LocalDate ld = LocalDate.now().with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));
        System.out.println("每月的最后一个星期日：" + ld);
        LocalDate ld1 = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        System.out.println("下一个星期一：" + ld1);
        LocalDate ld2 = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        System.out.println("上一个星期一：" + ld2);
        LocalDate ld3 = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("一个月的最后一天：" + ld3);
        LocalDate ld4 = LocalDate.now().minusDays(1);
        System.out.println("前一天：" + ld4);

        //公历转换
        LocalDate dd = LocalDate.now();
        //民国日期
        MinguoDate minguoDate = MinguoDate.from(dd);
        System.out.println(dd);
        //日本日期
        System.out.println(JapaneseDate.from(dd));
        //等价于当前日期
        ChronoLocalDate chronoLocalDate = JapaneseChronology.INSTANCE.dateNow();
        System.out.println(chronoLocalDate);
    }
}
