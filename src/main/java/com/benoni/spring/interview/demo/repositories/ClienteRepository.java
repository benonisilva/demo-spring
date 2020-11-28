package com.benoni.spring.interview.demo.repositories;

import java.util.List;

import com.benoni.spring.interview.demo.repositories.entities.ClienteEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
    List<ClienteEntity> findByNome(String nome);
}
