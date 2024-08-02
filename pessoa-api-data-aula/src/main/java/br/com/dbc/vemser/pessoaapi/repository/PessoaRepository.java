package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.dto.PessoaDTO;
import br.com.dbc.vemser.pessoaapi.dto.PessoaRelatorioDTO;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    List<Pessoa> findAllByNomeContainsIgnoreCase(String nome);

    Pessoa findByNomeIgnoreCase(String nome);

    Pessoa findByEmail(String email);

    //Queries de relacionamento
    List<Pessoa> findAllByPetsNotNull();
    Optional<Pessoa> findByIdPessoaAndPetsNotNull(Integer idPessoa);

    @Query("SELECT p FROM pessoa p WHERE p.cpf = :cpf")
    Optional<Pessoa> buscaPessoaPorCpf(@Param("cpf") String cpf);

    @Query("SELECT p FROM pessoa p WHERE p.email = :email")
    Optional<Pessoa> buscaPessoaPorEmail(@Param("email") String email);

    @Query(value = """
            SELECT ps.nome as nome_pessoa, p.nome as nome_pet FROM pessoa ps
            LEFT JOIN pet p
            ON ps.id_pessoa = p.id_pessoa
            WHERE p.id_pessoa IS NOT NULL
            """, nativeQuery = true)
    Optional<List<Object>> BuscaTodosEPetNotNull();


    @Query(value = "select distinct p from pessoa p join p.enderecos e where e is not null")
    List<Pessoa> procurarPessoasComEndereco();

    @Query("""
            SELECT new br.com.dbc.vemser.pessoaapi.dto.PessoaRelatorioDTO(
            p.idPessoa,
            p.nome,
            p.cpf,
            p.email,
            c.numero,
            pets.nome
            ) FROM pessoa p
            LEFT JOIN p.contatos c
            LEFT JOIN p.pets pets
            """)
    List<PessoaRelatorioDTO> buscaPessoasRelatorio();
}
