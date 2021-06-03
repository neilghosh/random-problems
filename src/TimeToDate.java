import static java.time.ZoneOffset.UTC;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

class TimeToDate {
    public static void main(String[] args) {
        testAddYearDuringLeapYear();
        testTimeToDateForTimeZones();
    }

    /**
     * The traditionally used Java.util.Date represents a specific moment of time like now(), or the moment I was born. It doesnt have any timezone 
     * its just a point in the arrow of time. People may want to translate it to their convenient timezone i.e. if its evening 7 PM in IST , it is 
     * also 1.30 PM in UTC. The moment of time does not change.
     * 
     * Problem is when someone gives you a moment of time and asks you what date it is. By saying date the person is expecting something like "1st Jan 20202"
     * or 2020-01-01 and doen't care about time. If he would have given me "1st Jan 2020 evening 7pm", I would have told him its "1st Jan 2020". 
     * 
     * Its probably true in most cases because we know the person probably cares only about his timezone. So no matter which 
     * timezone he is in the answer to the question is still "1st Jan 2020". However we can't confidently say that. If the person is telling the time (and date)
     * in his timezon say (UTC) and I am asnswering it to someone else who is in IST for the person I am answering to its already 2nd Jan because IST is 5.30 hours
     * ahead of UTC. So in a software system timezon of the person who is inputting time time is not nessesarily same as the other people using it.
     * 
     * So when we convert a point of time to just a Date value we need to consider date at what timezone. Same is the case when data is saved in java.util.Date which
     * is a point of time with no timezone information. While displaying date it could be different for different user based on their timezone.
     * 
     */
    private static void testTimeToDateForTimeZones() {
        print(LocalDate.of(2021, 1, 1), toDate(OffsetDateTime.of(2021, 1, 1, 23, 0, 0, 0, UTC), "Etc/UTC"),
                "Convert UTC to UTC");
        // If the time mentioned is 11pm at night at London, the date at Kolkata is next day.
        print(LocalDate.of(2021, 1, 2), toDate(OffsetDateTime.of(2021, 1, 1, 23, 0, 0, 0, UTC), "Asia/Kolkata"),
                "Convert UTC to IST");
        print(LocalDate.of(2021, 1, 1),
                toDate(OffsetDateTime.of(2021, 1, 1, 23, 0, 0, 0, ZoneOffset.of("+0530")), "Etc/UTC"),
                "Convert IST to UTC");
    }

    private static LocalDate toDate(OffsetDateTime dateTime, String zoneId) {
        return dateTime.toInstant().atZone(ZoneId.of(zoneId)).toLocalDate();
    }

    private static void testAddYearDuringLeapYear() {
        print(LocalDate.of(2021, 1, 1), LocalDate.of(2020, 1, 1).plusYears(1), "Add one year");
        print(LocalDate.of(2021, 2, 28), LocalDate.of(2020, 2, 29).plusYears(1), "Leap Year");
        print(LocalDate.of(2021, 2, 28), LocalDate.of(2020, 2, 28).plusYears(1), "Leap Year");
        print(LocalDate.of(2024, 2, 29), LocalDate.of(2020, 2, 29).plusYears(4), "Two Leap Years");
    }

    private static void print(LocalDate expected, LocalDate actual, String message) {
        System.out.println("Expected : " + expected.toString() + " Actual : " + actual.toString() + " - " + message);
    }
}