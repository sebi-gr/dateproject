package com.example.dateproject;

import com.example.dateproject.controller.DateController;
import com.example.dateproject.service.DateService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class DateTest {

    @Autowired
    private DateService dateService;

    @Autowired
    private DateController dateController;

    @Mock
    private DateService dateServiceMock;

    @InjectMocks
    private DateController dateControllerMock;

    @Test
    public void testGetTime() {
        assertThat(dateService.getTime()).isBeforeOrEqualTo(LocalTime.now());
    }

    @Test
    public void testGetTimeMock() {
        Mockito.when(dateServiceMock.getTime()).thenReturn(LocalTime.of(12,30,0,0));

        assertThat(dateServiceMock.getTime().getHour()).isEqualTo(12);
        assertThat(dateServiceMock.getTime().getMinute()).isEqualTo(30);
        assertThat(dateServiceMock.getTime().getSecond()).isEqualTo(0);
        assertThat(dateServiceMock.getTime().getNano()).isEqualTo(0);
    }

    @Test
    public void testGetDateFormatMock() {
        Mockito.when(dateControllerMock.getDate("dd-MM-yyyy")).thenReturn("24-06-2023");

        assertThat(dateControllerMock.getDate("dd-MM-yyyy")).isEqualTo("24-06-2023");
    }

    @Test
    public void testGetDateFormatNull() {
        assertThat(dateService.getDateWithFormat(null)).isEqualTo(LocalDate.now().toString());
    }

    @Test
    public void testInvalidFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dateService.getDateWithFormat("ddd-MM-yyyy");
        });
    }
}
