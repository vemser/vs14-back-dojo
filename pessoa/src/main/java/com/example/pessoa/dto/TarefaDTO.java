package com.example.pessoa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class TarefaDTO {
    private Integer id;
    private String descricao;
    private LocalDateTime dataCriacao;
    private boolean status;

}
