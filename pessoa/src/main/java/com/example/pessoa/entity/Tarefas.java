package com.example.pessoa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Tarefas {

    private Integer id;
    private String nomeTarefa;
    private String itens;

}
