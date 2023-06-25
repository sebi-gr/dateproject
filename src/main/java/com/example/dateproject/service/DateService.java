package com.example.dateproject.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class DateService {

    public LocalTime getTime() {
        return LocalTime.now();
    }

    public String getDateWithFormat(String format) {
        LocalDate localDate = LocalDate.now();
        if (format != null) {
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
                return dtf.format(localDate);
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
                throw new IllegalArgumentException();
            }
        }
        return localDate.toString();
    }
}
