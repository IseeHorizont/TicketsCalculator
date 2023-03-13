package ru.ideaplatform.testcase.utils;

import java.time.LocalDateTime;

public class DataTimeConverter {

    public static LocalDateTime convertToLocalDateTime(String date, String time) {
        String[] dateAfterSplit = date.split("\\.");
        String[] timeAfterSplit = time.split(":");
        return LocalDateTime.of(Integer.parseInt("20" + dateAfterSplit[2]),
                                Integer.parseInt(dateAfterSplit[1]),
                                Integer.parseInt(dateAfterSplit[0]),
                                Integer.parseInt(timeAfterSplit[0]),
                                Integer.parseInt(timeAfterSplit[1]), 0);
    }
}
