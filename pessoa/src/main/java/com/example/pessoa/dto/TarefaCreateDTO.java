package com.example.pessoa.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TarefaCreateDTO {

    @NotBlank
    private String nomeTarefa;

    @NotBlank
    private String itens;

}
