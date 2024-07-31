package br.com.dbc.vemser.aula_01_testes;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TipoProduto {
    HIGIENE(1),
    ALIMENTO(2),
    ELETRONICO(3),
    DECORACAO(4);

    private Integer tipo;

    TipoProduto(Integer tipo) {
        this.tipo = tipo;
    }

    public static TipoProduto ofTipo(Integer tipo){
        return Arrays.stream(TipoProduto.values())
                .filter(tp -> tp.getTipo().equals(tipo))
                .findFirst()
                .get();
    }
}
