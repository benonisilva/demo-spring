package com.benoni.spring.interview.demo.integracoes.estados;

import com.benoni.spring.interview.demo.integracoes.estados.model.ResponseEstado;

public interface IIntegracoesEstadoService {

    ResponseEstado getEstadoById(Integer id);
}
