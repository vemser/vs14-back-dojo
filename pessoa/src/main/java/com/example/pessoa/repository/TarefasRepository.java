package com.example.pessoa.repository;

import com.example.pessoa.entity.Tarefas;
import com.example.pessoa.exceptions.BDException;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TarefasRepository implements com.example.pessoa.repository.Repository<Integer, Tarefas> {

    private final Connection connection;

    @Override
    public Tarefas adicionar(Tarefas tarefas) throws SQLException {
    }

    @Override
    public boolean remover(Integer id) throws BDException {
        return false;
    }

    @Override
    public Tarefas editar(Integer id, Tarefas tarefas) throws BDException {
        return null;
    }

    @Override
    public List<Tarefas> listar() throws BDException {
        return List.of();
    }

    @Override
    public Tarefas listarPorId(Integer id) throws BDException {
        return null;
    }
}
