import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * Created by bing on 2017/3/23.
 */
public class MyTest {
    @Test
    public void testTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = "2017-01-01 12:12:12";
        String date1 = "2017-01-02 00:00:01";
        System.out.println(Period.between(LocalDate.parse(date, formatter), LocalDate.parse(date1, formatter)).getDays());
        System.out.println(Duration.between(LocalDateTime.parse(date,formatter), LocalDateTime.parse(date1,formatter)).getSeconds()/3600/24);
    }
}
