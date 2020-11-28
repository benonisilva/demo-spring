package com.benoni.spring.interview.demo.services;

import java.util.List;

import com.benoni.spring.interview.demo.api.model.Cliente;
import com.benoni.spring.interview.demo.repositories.entities.ClienteEntity;

public interface IMapperClienteService {
    List<Cliente> domainToModelList(List<ClienteEntity> ClientesDomain);

    List<ClienteEntity> modelToDomainList(List<Cliente> ClientesModel);

    Cliente domainToModel(ClienteEntity ClienteDomain);

    ClienteEntity modelToDomain(Cliente ClienteModel);

}
