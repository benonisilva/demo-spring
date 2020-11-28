package com.benoni.spring.interview.demo.mapper;

import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.benoni.spring.interview.demo.api.model.Cidade;
import com.benoni.spring.interview.demo.api.model.SIGLAS;
import com.benoni.spring.interview.demo.model.EstadosEnum;
import com.benoni.spring.interview.demo.repositories.entities.CidadeEntity;
import com.benoni.spring.interview.demo.repositories.entities.EstadoEntity;

@Configuration
public class CidadeMappers {

    /**
     * CidadeEntity to Cidade and vice versa
     * 
     * @return model mapper
     */
    @Bean
    public ModelMapper mapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        PropertyMap<CidadeEntity, Cidade> domainToModel = new PropertyMap<CidadeEntity, Cidade>() {
            protected void configure() {
                when(Conditions.isNotNull()).map().nome(source.getNome());
                when(Conditions.isNotNull()).map().estadoId(source.getEstadoId());
                when(Conditions.isNotNull()).using(ConverterEstado.estadoToNomeEstado).map(source.getEstado())
                        .estado(null);
                when(Conditions.isNotNull()).using(ConverterEstado.estadoToSiglaEstado).map(source.getEstado())
                        .setSigla(null);
            }
        };

        PropertyMap<Cidade, CidadeEntity> modelToDomain = new PropertyMap<Cidade, CidadeEntity>() {
            protected void configure() {

            }
        };
        modelMapper.addMappings(domainToModel);
        modelMapper.addMappings(modelToDomain);
        return modelMapper;
    }
}
