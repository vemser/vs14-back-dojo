package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dto.ContatoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.ContatoCustomDTO;
import br.com.dbc.vemser.pessoaapi.dto.ContatoDTO;
import br.com.dbc.vemser.pessoaapi.dto.PageDTO;
import br.com.dbc.vemser.pessoaapi.repository.ContatoRepository;
import br.com.dbc.vemser.pessoaapi.service.ContatoService;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contato")
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name = "Contato", description = "Endpoints para Contato")
public class ContatoController {

    private final ContatoService contatoService;
    private final ContatoRepository contatoRepository;

    @GetMapping // GET localhost:8080/contato
    public ResponseEntity<List<ContatoDTO>> list(
                                                ) throws RegraDeNegocioException {
        return new ResponseEntity<>(contatoService.listar(), HttpStatus.OK);
    }

    @GetMapping("/paginacao")
    public ResponseEntity<PageDTO<ContatoDTO>> listPaginado(@RequestParam(value = "pagina", required = false, defaultValue = "0") Integer pagina,
                                                            @RequestParam(value = "tamanho", required = false, defaultValue = "10") Integer tamanho) throws RegraDeNegocioException {
        return new ResponseEntity<>(contatoService.listarPaginado(pagina, tamanho), HttpStatus.OK);
    }

    @GetMapping("custom-dto") // GET localhost:8080/contato
    public ResponseEntity<List<ContatoCustomDTO>> findCustomDTO() throws RegraDeNegocioException {
        return new ResponseEntity<>(contatoRepository.buscaContatoDTO(), HttpStatus.OK);
    }

//    @GetMapping("/byIdPessoa") // GET localhost:8080/contato/byUserId?idUsuario=1
//    public ResponseEntity<List<ContatoDTO>> listByName(@RequestParam(value = "idPessoa") Integer idPessoa)
//            throws RegraDeNegocioException {
//        return new ResponseEntity<>(contatoService.listarPorIdUsuario(idPessoa), HttpStatus.OK);
//    }
//
//    @GetMapping("/{idContato}")
//    public ResponseEntity<ContatoDTO> getContato(@PathVariable(value = "idContato") Integer idContato) throws Exception {
//        return new ResponseEntity<>(contatoService.getContatoDTO(idContato), HttpStatus.OK);
//    }

    @PostMapping("/pessoa/{idPessoa}") // POST localhost:8080/contato
    public ResponseEntity<ContatoDTO> create(@PathVariable("idPessoa") Integer idPessoa, @Valid @RequestBody
    ContatoCreateDTO contatoCreateDTO) throws Exception {

        log.debug("Criando contato");
        ContatoDTO contatoCriado = contatoService.create(idPessoa, contatoCreateDTO);
        log.debug("Contato criado!");

        return new ResponseEntity<>(contatoCriado, HttpStatus.OK);
    }

//    @PutMapping("/{idContato}") // PUT localhost:8080/contato/1
//    public ResponseEntity<ContatoDTO> update(@PathVariable("idContato") Integer id,
//                         @Valid @RequestBody ContatoCreateDTO contatoAtualizar) throws Exception {
//
//        log.debug("Atualizando Contato");
//        ContatoDTO atualizado = contatoService.update(id, contatoAtualizar);
//        log.debug("Contato atualizado!");
//
//        return new ResponseEntity<>(atualizado, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{idContato}") // DELETE localhost:8080/contato/1
//    public ResponseEntity<Void> delete(@PathVariable("idContato") Integer id) throws Exception {
//
//        log.debug("Removendo contato");
//        contatoService.delete(id);
//        log.debug("Contato removido!");
//
//        return ResponseEntity.ok().build();
//    }

}
