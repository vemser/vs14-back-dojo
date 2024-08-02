package br.com.dbc.vemser.pessoaapi.mocks;

import br.com.dbc.vemser.pessoaapi.dto.PessoaCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.PessoaDTO;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;

import java.time.LocalDate;

public class PessoaMock {

    public Pessoa retornarPessoaEntity(Integer numero) {
        Pessoa pessoaEntity = new Pessoa();
        pessoaEntity.setIdPessoa(numero);
        pessoaEntity.setNome("Pedro" + numero);
        pessoaEntity.setDataNascimento(LocalDate.parse("2000-01-01"));
        pessoaEntity.setCpf("1231231231" + numero);
        pessoaEntity.setEmail("pedro" + numero + "@dbccompany.com.br");

        return pessoaEntity;
    }

    public PessoaCreateDTO retornarPessoaCreateDto(Integer numero) {
        PessoaCreateDTO pessoaCreateDTO = new PessoaCreateDTO();
        pessoaCreateDTO.setNome("Pedro" + numero);
        pessoaCreateDTO.setDataNascimento(LocalDate.parse("2000-01-01"));
        pessoaCreateDTO.setCpf("1231231231" + numero);
        pessoaCreateDTO.setEmail("pedro" + numero + "@dbccompany.com.br");

        return pessoaCreateDTO;
    }

    public PessoaCreateDTO retornarPessoaUpdateDto(Integer numero) {
        PessoaCreateDTO pessoaCreateDTO = new PessoaCreateDTO();
        pessoaCreateDTO.setNome("Pedro editado" + numero);
        pessoaCreateDTO.setDataNascimento(LocalDate.parse("2000-02-01"));
        pessoaCreateDTO.setCpf("1231231223" + numero);
        pessoaCreateDTO.setEmail("pedroeditado" + numero + "@dbccompany.com.br");

        return pessoaCreateDTO;
    }

    public PessoaDTO retornarPessoaDTO(Integer numero) {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setIdPessoa(numero);
        pessoaDTO.setNome("Pedro" + numero);
        pessoaDTO.setDataNascimento(LocalDate.parse("2000-01-01"));
        pessoaDTO.setCpf("1231231231" + numero);
        pessoaDTO.setEmail("pedro" + numero + "@dbccompany.com.br");

        return pessoaDTO;
    }
}
