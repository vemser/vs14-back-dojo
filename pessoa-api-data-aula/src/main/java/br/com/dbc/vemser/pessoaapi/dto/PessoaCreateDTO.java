package br.com.dbc.vemser.pessoaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public class PessoaCreateDTO {

    @NotBlank
    @Size(min = 3, max = 40)
    private String nome;

    @NotNull
    @Past
    private LocalDate dataNascimento;

    @NotNull
    @Size(min = 11, max = 11)
    private String cpf;

    @NotBlank
    @Email
    private String email;
}
