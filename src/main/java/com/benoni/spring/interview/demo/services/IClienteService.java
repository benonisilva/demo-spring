package com.benoni.spring.interview.demo.services;

import java.util.List;

import com.benoni.spring.interview.demo.api.model.Cliente;

public interface IClienteService {
    Cliente cadastrarCliente(Cliente cliente);

    boolean exists(Integer idCliente);

    Cliente updateCliente(Cliente cliente, Integer idCliente);

    Integer delete(Integer idCliente);

    List<Cliente> consultarClienteByNome(String nome);
}
