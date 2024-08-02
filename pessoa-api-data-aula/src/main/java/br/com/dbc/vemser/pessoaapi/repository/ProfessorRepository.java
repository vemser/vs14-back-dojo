package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.entity.Professor;
import br.com.dbc.vemser.pessoaapi.entity.pk.ProfessorPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, ProfessorPK> {
}
