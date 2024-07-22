package com.example.pessoa.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TarefaCreateDTO {

    @NotNull
    private Integer id;

    @NotBlank
    private String nomeTarefa;

    @NotBlank
    private String itens;

}
