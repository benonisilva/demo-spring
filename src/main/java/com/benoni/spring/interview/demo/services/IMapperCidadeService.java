package com.benoni.spring.interview.demo.services;

import java.util.List;

import com.benoni.spring.interview.demo.api.model.Cidade;
import com.benoni.spring.interview.demo.repositories.entities.CidadeEntity;

public interface IMapperCidadeService {
    List<Cidade> domainToModelList(List<CidadeEntity> cidadesDomain);

    List<CidadeEntity> modelToDomainList(List<Cidade> cidadesModel);

    Cidade domainToModel(CidadeEntity cidadeDomain);

    CidadeEntity modelToDomain(Cidade cidadeModel);

}
