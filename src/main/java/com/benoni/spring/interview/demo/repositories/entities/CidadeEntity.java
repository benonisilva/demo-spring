package com.benoni.spring.interview.demo.repositories.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CascadeType;

@Entity
public class CidadeEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1869855275144653569L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "estadoId")
    private Integer estadoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estadoId", nullable = false, insertable = false, updatable = false)
    private EstadoEntity estado;

    public CidadeEntity() {

    }

    public CidadeEntity(Integer id, String nome, Integer estadoId) {
        this.id = id;
        this.nome = nome;
        this.estadoId = estadoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((estadoId == null) ? 0 : estadoId.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CidadeEntity other = (CidadeEntity) obj;
        if (estadoId == null) {
            if (other.estadoId != null)
                return false;
        } else if (!estadoId.equals(other.estadoId))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

    public EstadoEntity getEstado() {
        return estado;
    }

}
