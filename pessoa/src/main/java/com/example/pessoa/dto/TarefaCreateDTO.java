package com.example.pessoa.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class TarefaCreateDTO {
    @NotBlank
    private String descricao;

    @PastOrPresent
    private LocalDateTime dataCriacao;

    @NotNull
    private boolean status;
}
