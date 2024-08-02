package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dto.*;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;
import br.com.dbc.vemser.pessoaapi.service.PessoaService;
import br.com.dbc.vemser.pessoaapi.entity.PropertieReader;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pessoa") // localhost:8080/pessoa
@Validated
@Slf4j
@Tag(name = "Pessoa", description = "Endpoints para Pessoa")
public class PessoaController {

    private final PessoaService pessoaService;
    private final PessoaRepository pessoaRepository;
    private final PropertieReader propertieReader;

    @GetMapping("/ambiente")
    public ResponseEntity<String> ambiente() {
        return new ResponseEntity<>(propertieReader.getAmbiente(), HttpStatus.OK);
    }

    @GetMapping // GET localhost:8080/pessoa
    public ResponseEntity<PageDTO<PessoaDTO>> findAll(@RequestParam(value = "pagina", required = false, defaultValue = "0") Integer pagina,
                                                   @RequestParam(value = "tamanho", required = false, defaultValue = "10") Integer tamanho,
                                                      @RequestParam(value = "filter", required = false) String filter,
                                                      @RequestParam(value = "sort", required = false, defaultValue = "ASCENDING") String sort
    ) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaService.list(pagina, tamanho, filter, sort), HttpStatus.OK);
    }

    @GetMapping("/relatorio") // GET localhost:8080/pessoa
    public ResponseEntity<List<PessoaRelatorioDTO>> findAllRelatorio() throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaRepository.buscaPessoasRelatorio(), HttpStatus.OK);
    }

    @GetMapping("/{idPessoa}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable Integer idPessoa) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaService.findById(idPessoa), HttpStatus.OK);
    }

    @GetMapping("/com-pets-custom")
    public ResponseEntity<Optional<List<Object>>> findByPetsCustom() throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaRepository.BuscaTodosEPetNotNull(), HttpStatus.OK);
    }

    @GetMapping("/com-contatos")
    public ResponseEntity<List<PessoaComContatoDTO>> findByContato(@RequestParam(value = "idPessoa", required = false) Integer idPessoa) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaService.findAllWithContato(idPessoa), HttpStatus.OK);
    }

    @GetMapping("/com-enderecos")
    public ResponseEntity<List<PessoaComEnderecoDTO>> findByEndereco(@RequestParam(value = "idPessoa", required = false) Integer idPessoa) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaService.findAllWithEndereco(idPessoa), HttpStatus.OK);
    }

    @GetMapping("/com-enderecos-custom")
    public ResponseEntity<List<Pessoa>> findByEnderecoCustom() throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaRepository.procurarPessoasComEndereco(), HttpStatus.OK);
    }

    @GetMapping("/com-pets")
    public ResponseEntity<List<PessoaComPetsDTO>> findByPets(@RequestParam(value = "idPessoa", required = false) Integer idPessoa) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaService.findAllWithPets(idPessoa), HttpStatus.OK);
    }

    @GetMapping("/com-pets-repo")
    public ResponseEntity<List<PessoaComPetsDTO>> findByPetsRepo(@RequestParam(value = "idPessoa", required = false) Integer idPessoa) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaService.findAllWithPetsInRepo(idPessoa), HttpStatus.OK);
    }

    @GetMapping("/bycpf") // GET localhost:8080/pessoa?id_pessoa=1
    public ResponseEntity<PessoaDTO> findByCpf(@RequestParam(value = "cpf") String cpf) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaService.findByCpf(cpf), HttpStatus.OK);
    }

    @GetMapping("/bynome") // GET localhost:8080/pessoa?id_pessoa=1
    public ResponseEntity<List<PessoaDTO>> findByName(@RequestParam(value = "nome") String nome) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaService.findAllByNome(nome), HttpStatus.OK);
    }

    @PostMapping // POST localhost:8080/pessoa
    public ResponseEntity<PessoaDTO> create(@Valid @RequestBody PessoaCreateDTO pessoaCreateDTO) throws RegraDeNegocioException {

        log.debug("Criando pessoa");
        PessoaDTO pessoaCriada = pessoaService.create(pessoaCreateDTO);
        log.debug("Pessoa Criada!");

        return new ResponseEntity<>(pessoaCriada, HttpStatus.OK);
    }

    @PutMapping("/{idPessoa}") // PUT localhost:8080/pessoa/1000
    public ResponseEntity<PessoaDTO> update(@PathVariable("idPessoa") Integer id,
                                            @Valid @RequestBody PessoaCreateDTO pessoaAtualizar) throws RegraDeNegocioException {

        log.debug("Atualizando Pessoa");
        PessoaDTO pessoaAtualizada = pessoaService.update(id, pessoaAtualizar);
        log.debug("Pessoa atualizada!");

        return new ResponseEntity<>(pessoaAtualizada, HttpStatus.OK);
    }


    @DeleteMapping("/{idPessoa}") // DELETE localhost:8080/pessoa/10
    public ResponseEntity<Void> delete(@PathVariable("idPessoa") Integer id) throws Exception {
        log.debug("Removendo pessoa");
        pessoaService.delete(id);
        log.debug("Pessoa removida!");

        return ResponseEntity.ok().build();
    }

}

