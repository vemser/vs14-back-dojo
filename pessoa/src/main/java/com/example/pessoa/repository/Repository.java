package com.example.pessoa.repository;


import br.com.dbc.vemser.varejodelivros.exceptions.BancoDeDadosException;

import java.util.List;

public interface Repository<CHAVE, OBJETO> {

    OBJETO adicionar(OBJETO object) throws BancoDeDadosException;

    boolean remover(CHAVE id) throws BancoDeDadosException;

    OBJETO editar(CHAVE id, OBJETO objeto) throws BancoDeDadosException;

    List<OBJETO> listar() throws BancoDeDadosException;

    OBJETO listarPorId(CHAVE id) throws BancoDeDadosException;
}
