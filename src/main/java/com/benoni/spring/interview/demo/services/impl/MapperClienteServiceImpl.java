package com.benoni.spring.interview.demo.services.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.benoni.spring.interview.demo.api.model.Cliente;
import com.benoni.spring.interview.demo.mapper.ClienteMappers;
import com.benoni.spring.interview.demo.repositories.entities.ClienteEntity;
import com.benoni.spring.interview.demo.services.IMapperClienteService;

import org.modelmapper.TypeToken;

@Named
public class MapperClienteServiceImpl implements IMapperClienteService {

    @Inject
    private ClienteMappers mapper;

    @Override
    public List<Cliente> domainToModelList(List<ClienteEntity> ClientesDomain) {
        List<Cliente> retVal = mapper.mapperCliente().map(ClientesDomain, new TypeToken<List<Cliente>>() {
        }.getType());
        return retVal;
    }

    @Override
    public List<ClienteEntity> modelToDomainList(List<Cliente> ClientesModel) {
        List<ClienteEntity> retVal = mapper.mapperCliente().map(ClientesModel, new TypeToken<List<Cliente>>() {
        }.getType());
        return retVal;
    }

    @Override
    public Cliente domainToModel(ClienteEntity ClienteDomain) {
        Cliente retVal = mapper.mapperCliente().map(ClienteDomain, Cliente.class);
        return retVal;
    }

    @Override
    public ClienteEntity modelToDomain(Cliente ClienteModel) {
        ClienteEntity retVal = mapper.mapperCliente().map(ClienteModel, ClienteEntity.class);
        return retVal;
    }

}
