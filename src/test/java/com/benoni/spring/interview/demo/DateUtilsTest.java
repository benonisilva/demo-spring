package com.benoni.spring.interview.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

import java.util.Calendar;

import com.benoni.spring.interview.demo.utils.DateUtils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DateUtilsTest {

    @Test
    public void count_year_18() {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.set(2000, 01, 01);
        c2.set(2018, 01, 01);
        Integer target = DateUtils.countYears(c2, c1);
        assertEquals(18, target);
    }

    @Test
    public void get_20_years() {
        Calendar c1 = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        c1.set(2000, 1, 1);
        today.set(2020, 1, 1);

        try (MockedStatic<Calendar> mocked = mockStatic(Calendar.class)) {
            mocked.when(Calendar::getInstance).thenReturn(today);
            Integer target = DateUtils.getAge(c1);
            assertEquals(20, target);
            mocked.clearInvocations();
        }

    }

    // @Test
    public void gteste_fronteira_mes_anterior() {
        Calendar c1 = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        c1.set(2000, 2, 1);
        today.set(2020, 1, 1);

        try (MockedStatic<Calendar> mocked = mockStatic(Calendar.class)) {
            mocked.when(Calendar::getInstance).thenReturn(today);
            Integer target = DateUtils.getAge(c1);
            assertEquals(19, target);
            mocked.clearInvocations();
        }

    }
}
