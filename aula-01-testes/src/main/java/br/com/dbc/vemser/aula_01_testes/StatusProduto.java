package br.com.dbc.vemser.aula_01_testes;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum StatusProduto {
    ATIVO(1),
    INATIVO(2);

    private Integer tipo;

    StatusProduto(Integer tipo) {
        this.tipo = tipo;
    }

    public static StatusProduto ofTipo(Integer tipo){
        return Arrays.stream(StatusProduto.values())
                .filter(tp -> tp.getTipo().equals(tipo))
                .findFirst()
                .get();
    }
}
