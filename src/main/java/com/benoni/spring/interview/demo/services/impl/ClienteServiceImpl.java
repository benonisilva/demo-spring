package com.benoni.spring.interview.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.benoni.spring.interview.demo.api.model.Cliente;
import com.benoni.spring.interview.demo.repositories.ClienteRepository;
import com.benoni.spring.interview.demo.repositories.entities.ClienteEntity;
import com.benoni.spring.interview.demo.services.IClienteService;
import com.benoni.spring.interview.demo.services.IMapperClienteService;
import com.benoni.spring.interview.demo.services.NotFoundException;

@Named
public class ClienteServiceImpl implements IClienteService {

    @Inject
    private ClienteRepository repositoryCliente;

    @Inject
    private IMapperClienteService mapper;

    @Override
    public Cliente cadastrarCliente(Cliente cliente) {
        Cliente savedCliente;
        ClienteEntity clienteToSave = mapper.modelToDomain(cliente);
        ClienteEntity clienteSaved = repositoryCliente.save(clienteToSave);
        savedCliente = mapper.domainToModel(clienteSaved);
        return savedCliente;
    }

    @Override
    public boolean exists(Integer idCliente) {
        boolean retVal = repositoryCliente.existsById(idCliente);
        return retVal;
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        boolean existsCliente = exists(cliente.getId() != null ? cliente.getId() : 0);
        Cliente updatedCliente;
        if (existsCliente) {
            ClienteEntity clienteToUpdate = mapper.modelToDomain(cliente);
            repositoryCliente.save(clienteToUpdate);
            updatedCliente = mapper.domainToModel(clienteToUpdate);
        } else {
            throw new NotFoundException();
        }
        return updatedCliente;
    }

    @Override
    public Integer delete(Integer idCliente) {
        boolean existsCliente = exists(idCliente);
        if (existsCliente) {
            repositoryCliente.deleteById(idCliente);
        } else {
            throw new NotFoundException();
        }
        return idCliente;
    }

    @Override
    public List<Cliente> consultarClienteByNome(String nome) {
        List<ClienteEntity> clienteDomain;
        List<Cliente> retVal = new ArrayList<>();
        if (nome != null && !nome.isEmpty()) {
            clienteDomain = repositoryCliente.findByNome(nome);
            retVal = mapper.domainToModelList(clienteDomain);
        } else {
            clienteDomain = repositoryCliente.findAll();
            retVal = mapper.domainToModelList(clienteDomain);
        }

        return retVal;
    }

    @Override
    public Cliente getById(Integer id) {
        boolean existsCliente = exists(id);
        Cliente cliente;
        if (existsCliente) {
            ClienteEntity clienteEntity = repositoryCliente.getOne(id);
            cliente = mapper.domainToModel(clienteEntity);
        } else {
            throw new NotFoundException();
        }
        return cliente;
    }

}
