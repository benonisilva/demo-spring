package com.benoni.spring.interview.demo.services.impl;

import java.util.List;

import com.benoni.spring.interview.demo.api.model.Cliente;
import com.benoni.spring.interview.demo.services.IClienteService;
import com.benoni.spring.interview.demo.services.NotFoundException;

public class ClienteServiceImpl implements IClienteService {

    @Override
    public Cliente cadastrarCliente(Cliente cliente) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean exists(Integer idCliente) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Cliente updateCliente(Cliente cliente, Integer idCliente) {
        boolean existsCliente = exists(idCliente);
        if (existsCliente) {

        } else {
            throw new NotFoundException();
        }
        return null;
    }

    @Override
    public Integer delete(Integer idCliente) {
        boolean existsCliente = exists(idCliente);
        if (existsCliente) {

        } else {
            throw new NotFoundException();
        }
        return null;
    }

    @Override
    public List<Cliente> consultarClienteByNome(String nome) {
        // TODO Auto-generated method stub
        return null;
    }

}
