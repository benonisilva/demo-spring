package com.benoni.spring.interview.demo.mapper;

import java.util.Calendar;

import com.benoni.spring.interview.demo.utils.DateUtils;

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
}
