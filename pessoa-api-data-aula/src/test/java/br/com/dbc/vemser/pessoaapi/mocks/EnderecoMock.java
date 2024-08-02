package br.com.dbc.vemser.pessoaapi.mocks;

import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.entity.TipoEndereco;

public class EnderecoMock {

    public Endereco retornarEnderecoEntity(Integer numero) {

        Endereco endereco = new Endereco();
        endereco.setIdEndereco(numero);
        endereco.setTipo(TipoEndereco.ofTipo(1));
        endereco.setLogradouro("Endereco " + numero);
        endereco.setNumero(numero);
        endereco.setComplemento("Próximo a escola");
        endereco.setCep("5858588");
        endereco.setCidade("Campina Grande");
        endereco.setEstado("Paraiba");
        endereco.setPais("Brasil");

        return endereco;
    }



}
