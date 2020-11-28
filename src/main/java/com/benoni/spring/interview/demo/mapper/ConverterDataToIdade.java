package com.benoni.spring.interview.demo.mapper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.benoni.spring.interview.demo.utils.DateUtils;

import org.joda.time.LocalDate;
import org.modelmapper.Converter;

public class ConverterDataToIdade {

    private ConverterDataToIdade() {

    }

    public static final Converter<Calendar, Integer> dataToIdade = ctx -> {
        int idade = 0;
        Calendar dataNascimento = ctx.getSource();
        idade = DateUtils.getAge(dataNascimento);
        return idade;
    };

    public static final Converter<Calendar, LocalDate> dateToLocalDate = ctx -> {
        LocalDate retVal;
        Calendar dataNascimento = ctx.getSource();
        int year = dataNascimento.get(Calendar.YEAR);
        int month = dataNascimento.get(Calendar.MONTH);
        int day = dataNascimento.get(Calendar.DATE);
        retVal = new LocalDate(year, month + 1, day);
        return retVal;
    };

    public static final Converter<LocalDate, Calendar> localDateToCalendar = ctx -> {
        Calendar retVal = Calendar.getInstance();
        LocalDate dataNascimento = ctx.getSource();
        int year = dataNascimento.getYear();
        int month = dataNascimento.getMonthOfYear() + 1;
        int day = dataNascimento.getDayOfMonth();
        retVal.set(year, month, day);
        return retVal;
    };
}
