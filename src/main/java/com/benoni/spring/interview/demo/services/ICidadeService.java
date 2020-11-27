package com.benoni.spring.interview.demo.services;

import java.util.List;

import com.benoni.spring.interview.demo.api.model.Cidade;

public interface ICidadeService {
    Cidade cadastrarCidade(Cidade cidade);

    List<Cidade> consultarCidadeByNomeAndEstadoId(String nome, Integer EstadoId);
}
