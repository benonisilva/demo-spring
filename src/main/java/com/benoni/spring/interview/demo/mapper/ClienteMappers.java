package com.benoni.spring.interview.demo.mapper;

import com.benoni.spring.interview.demo.api.model.Cliente;
import com.benoni.spring.interview.demo.repositories.entities.ClienteEntity;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteMappers {

    @Bean
    public ModelMapper mapperCliente() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        PropertyMap<ClienteEntity, Cliente> domainToModel = new PropertyMap<ClienteEntity, Cliente>() {
            protected void configure() {
                when(Conditions.isNotNull()).using(ConverterDataToIdade.dataToIdade).map(source.getDataNascimento())
                        .idade(null);
                // when(Conditions.isNotNull()).using(estadoToSiglaEstado).map(source.getEstado()).setSigla(null);
            }
        };

        PropertyMap<Cliente, ClienteEntity> modelToDomain = new PropertyMap<Cliente, ClienteEntity>() {
            protected void configure() {

            }
        };
        modelMapper.addMappings(domainToModel);
        modelMapper.addMappings(modelToDomain);
        return modelMapper;
    }

}
