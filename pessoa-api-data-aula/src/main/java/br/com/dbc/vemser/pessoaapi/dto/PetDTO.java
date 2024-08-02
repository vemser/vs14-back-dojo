package br.com.dbc.vemser.pessoaapi.dto;

import br.com.dbc.vemser.pessoaapi.entity.TipoPet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetDTO {
    private Integer idPet;
    private Integer idPessoa;
    private String nome;
    private TipoPet tipo;
}
