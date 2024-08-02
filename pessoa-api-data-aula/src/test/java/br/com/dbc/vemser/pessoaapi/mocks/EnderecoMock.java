package br.com.dbc.vemser.pessoaapi.mocks;

import br.com.dbc.vemser.pessoaapi.entity.Endereco;

public class EnderecoMock {

    public Endereco retornarEnderecoEntity(Integer numero) {

        Endereco endereco = new Endereco();
        endereco.setIdEndereco(numero);
        endereco.setTipo();
        endereco.setLogradouro("Endereco " + numero);
        endereco.setNumero(numero);
        endereco.setComplemento();
    }



}
