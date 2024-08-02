package br.com.dbc.vemser.pessoaapi.dto;

import br.com.dbc.vemser.pessoaapi.entity.TipoContato;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class ContatoCreateDTO {
    private Integer idContato;
    private Integer idPessoa;

    @NotNull
    private TipoContato tipoContato;

    @NotBlank
    @Size(max = 13)
    private String numero;

    @NotBlank
    private String descricao;
}
