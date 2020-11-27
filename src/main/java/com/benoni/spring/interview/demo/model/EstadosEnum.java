package com.benoni.spring.interview.demo.model;

import java.util.Arrays;

public enum EstadosEnum {

    // TODO acrescentar mais estados
    ACRE(1, "Acre", "AC"), ALAGOAS(2, "Alagoas", "AL"), PERNBUCO(17, "Pernambuco", "PE");

    private Integer id;
    private String nome;
    private String sigla;

    private EstadosEnum(Integer id, String nome, String sigla) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }

    public static EstadosEnum fromId(Integer id) {
        return Arrays.stream(EstadosEnum.values()).filter(e -> e.id.equals(id)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getNome() {
        return this.nome;
    }
}
