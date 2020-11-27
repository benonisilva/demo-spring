package com.benoni.spring.interview.demo.integracoes.estados.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.benoni.spring.interview.demo.integracoes.RestClientBase;
import com.benoni.spring.interview.demo.integracoes.estados.IIntegracoesEstadoService;
import com.benoni.spring.interview.demo.integracoes.estados.model.ResponseEstado;

import reactor.core.publisher.Mono;

@Named
public class IntegracoesEstadoServiceImpl implements IIntegracoesEstadoService {

    @Inject
    private RestClientBase<ResponseEstado> restCliente;

    private static String PATH = "/localidades/estados/";

    @Override
    public ResponseEstado getEstadoById(Integer id) {
        ResponseEstado estado = restCliente.get(PATH + id, ResponseEstado.class)
                .onErrorResume(this::handlerWebclientError).block();
        return estado;
    }

    private Mono<ResponseEstado> handlerWebclientError(Throwable exception) {
        return Mono.just(new ResponseEstado());
    }
}
