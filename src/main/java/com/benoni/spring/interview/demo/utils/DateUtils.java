package com.benoni.spring.interview.demo.utils;

import java.util.Calendar;

public class DateUtils {
    private DateUtils() {
    }

    public static Integer countYears(Calendar from, Calendar to) {
        int diff = from.get(Calendar.YEAR) - to.get(Calendar.YEAR);
        if (from.get(Calendar.MONTH) < to.get(Calendar.MONTH) || (from.get(Calendar.MONTH) == to.get(Calendar.MONTH)
                && to.get(Calendar.DATE) > from.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }

    public static Integer getAge(Calendar dataNascimento) {
        int years = 0;
        Calendar today = Calendar.getInstance();
        years = countYears(today, dataNascimento);
        return years;
    }

}
