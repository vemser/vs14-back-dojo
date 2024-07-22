package com.example.pessoa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Tarefas {

    private Integer id;
    private String descricao;
    private LocalDateTime dataCriacao;
    private boolean status;

}
