package com.benoni.spring.interview.demo.services.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.benoni.spring.interview.demo.api.model.Cidade;
import com.benoni.spring.interview.demo.mapper.CidadeMappers;
import com.benoni.spring.interview.demo.repositories.entities.CidadeEntity;
import com.benoni.spring.interview.demo.services.IMapperCidadeService;

import org.modelmapper.TypeToken;

@Named
public class MapperCidadeServiceImpl implements IMapperCidadeService {

    @Inject
    private CidadeMappers mapper;

    @Override
    public List<Cidade> domainToModelList(List<CidadeEntity> cidadesDomain) {
        List<Cidade> retVal = mapper.mapper().map(cidadesDomain, new TypeToken<List<Cidade>>() {
        }.getType());
        return retVal;
    }

    @Override
    public List<CidadeEntity> modelToDomainList(List<Cidade> cidadesModel) {
        List<CidadeEntity> retVal = mapper.mapper().map(cidadesModel, new TypeToken<List<Cidade>>() {
        }.getType());
        return retVal;
    }

    @Override
    public Cidade domainToModel(CidadeEntity cidadeDomain) {
        Cidade retVal = mapper.mapper().map(cidadeDomain, Cidade.class);
        return retVal;
    }

    @Override
    public CidadeEntity modelToDomain(Cidade cidadeModel) {
        CidadeEntity retVal = mapper.mapper().map(cidadeModel, CidadeEntity.class);
        return retVal;
    }

}
