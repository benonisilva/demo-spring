package com.benoni.spring.interview.demo.repositories;

import java.util.List;

import com.benoni.spring.interview.demo.repositories.entities.CidadeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadesRepository extends JpaRepository<CidadeEntity, Integer> {
    List<CidadeEntity> findByNomeOrEstadoId(String nome, Integer estadoId);
}
