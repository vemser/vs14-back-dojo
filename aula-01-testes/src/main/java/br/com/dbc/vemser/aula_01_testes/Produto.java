package br.com.dbc.vemser.aula_01_testes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Produto {
    private Integer idProduto;
    private String nome;
    private double peso;
    private StatusProduto status;
    private TipoProduto tipo;

    public Produto(String nome, double peso, StatusProduto status, TipoProduto tipo) {
        this.nome = nome;
        this.peso = peso;
        this.status = status;
        this.tipo = tipo;
    }
}
