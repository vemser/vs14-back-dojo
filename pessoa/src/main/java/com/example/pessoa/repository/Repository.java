package com.example.pessoa.repository;

import com.example.pessoa.exceptions.BDException;

import java.sql.SQLException;
import java.util.List;

public interface Repository<CHAVE, OBJETO> {

    OBJETO adicionar(OBJETO object) throws Exception;

    boolean remover(CHAVE id) throws BDException;

    OBJETO editar(CHAVE id, OBJETO objeto) throws BDException;

    List<OBJETO> listar() throws BDException;

    OBJETO listarPorId(CHAVE id) throws BDException;
}