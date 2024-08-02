package br.com.dbc.vemser.pessoaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PessoaRelatorioDTO {
    private Integer idPessoa;
    private String nomePessoa;
    private String cpf;
    private String email;
    private String numero;
    private String petNome;
}
