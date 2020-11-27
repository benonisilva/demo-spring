package com.benoni.spring.interview.demo.services.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.benoni.spring.interview.demo.api.model.Cidade;
import com.benoni.spring.interview.demo.repositories.CidadesRepository;
import com.benoni.spring.interview.demo.repositories.entities.CidadeEntity;
import com.benoni.spring.interview.demo.services.ICidadeService;
import com.benoni.spring.interview.demo.services.IMapperCidadeService;

@Named
public class CidadeServiceImpl implements ICidadeService {

    @Inject
    private CidadesRepository cidadeRepository;

    @Inject
    private IMapperCidadeService mapperCidadeService;

    @Override
    public Cidade cadastrarCidade(Cidade cidade) {
        CidadeEntity cidadeToSave = mapperCidadeService.modelToDomain(cidade);
        CidadeEntity newCidade = cidadeRepository.save(cidadeToSave);
        Cidade cidadeModel = mapperCidadeService.domainToModel(newCidade);
        return cidadeModel;
    }

    @Override
    public List<Cidade> consultarCidadeByNomeAndEstadoId(String nome, Integer estadoId) {
        List<CidadeEntity> cidadesDomain;
        if (nome != null && !nome.isEmpty() || estadoId != null) {
            cidadesDomain = cidadeRepository.findByNomeOrEstadoId(nome, estadoId);
        } else {
            cidadesDomain = cidadeRepository.findAll();
        }

        List<Cidade> retVal = mapperCidadeService.domainToModelList(cidadesDomain);
        return retVal;
    }

}
