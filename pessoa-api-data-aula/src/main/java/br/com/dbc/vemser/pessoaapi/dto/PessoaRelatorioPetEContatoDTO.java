package br.com.dbc.vemser.pessoaapi.dto;

import br.com.dbc.vemser.pessoaapi.entity.Contato;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class PessoaRelatorioPetEContatoDTO {
    private Integer idPessoa;
    private String nomePessoa;
    private String cpf;
    private String email;
    private Set<Contato> contatos;
    private String petNome;
}
