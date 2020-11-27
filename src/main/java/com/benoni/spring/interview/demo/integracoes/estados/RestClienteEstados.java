package com.benoni.spring.interview.demo.integracoes.estados;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.benoni.spring.interview.demo.integracoes.RestClientBase;
import com.benoni.spring.interview.demo.integracoes.estados.model.ResponseEstado;

import org.springframework.beans.factory.annotation.Value;

@Named
public class RestClienteEstados extends RestClientBase<ResponseEstado> {

    @Value("${app.integracoes}")
    private String endpoint;

    @PostConstruct
    public void init() {
        create(endpoint);
    }
}
