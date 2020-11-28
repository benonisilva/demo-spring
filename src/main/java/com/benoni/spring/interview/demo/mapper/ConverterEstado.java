package com.benoni.spring.interview.demo.mapper;

import com.benoni.spring.interview.demo.api.model.SIGLAS;
import com.benoni.spring.interview.demo.repositories.entities.EstadoEntity;

import org.modelmapper.Converter;

public class ConverterEstado {
    private ConverterEstado() {
    }

    public static Converter<EstadoEntity, SIGLAS> estadoToSiglaEstado = ctx -> {
        EstadoEntity estado = ctx.getSource();
        SIGLAS sigla = SIGLAS.fromValue(estado.getSigla());
        return sigla;

    };

    public static Converter<EstadoEntity, String> estadoToNomeEstado = ctx -> {
        EstadoEntity estado = ctx.getSource();
        return estado.getNome();

    };

}
