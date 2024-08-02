package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.dto.ContatoCustomDTO;
import br.com.dbc.vemser.pessoaapi.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {

    String dtoCustomPath = "br.com.dbc.vemser.pessoaapi.dto.ContatoCustomDTO";

    @Query("SELECT new " + dtoCustomPath + "(" +
            "p.nome, " +
            "c.numero " +
            ") FROM Contato c " +
            "LEFT JOIN c.pessoa p")
    List<ContatoCustomDTO> buscaContatoDTO();

    List<Contato> findAllByDescricaoContainsIgnoreCase(String descricao);
}
