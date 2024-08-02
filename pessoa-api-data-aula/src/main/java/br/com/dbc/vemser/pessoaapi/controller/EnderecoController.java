package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.dbc.vemser.pessoaapi.dto.PageDTO;
import br.com.dbc.vemser.pessoaapi.service.EnderecoService;
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
@RequestMapping("/endereco")
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name = "Endereco", description = "Endpoints para Endereço")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> listar() throws RegraDeNegocioException {
        return new ResponseEntity<>(enderecoService.listar(), HttpStatus.OK);
    }

    @GetMapping("/paginacao")
    public ResponseEntity<PageDTO<EnderecoDTO>> listarPaginado(@RequestParam(value = "pagina", required = false, defaultValue = "0") Integer pagina,
                                                               @RequestParam(value = "tamanho", required = false, defaultValue = "10") Integer tamanho) throws RegraDeNegocioException {
        return new ResponseEntity<>(enderecoService.listarPaginado(pagina, tamanho), HttpStatus.OK);
    }

//    @GetMapping("/{idEndereco}")
//    public ResponseEntity<EnderecoDTO> getEnderecoPorId(@PathVariable(value = "idEndereco") Integer idEndereco)
//            throws Exception {
//        return new ResponseEntity<>(enderecoService.,(idEndereco), HttpStatus.OK);
//    }

//    @GetMapping("/pessoa/{idPessoa}")
//    public ResponseEntity<List<EnderecoDTO>> listarEnderecosPorPessoa(@PathVariable(value = "idPessoa") Integer idPessoa)
//            throws RegraDeNegocioException {
//        return new ResponseEntity<>(enderecoService.listarPorIdPessoa(idPessoa), HttpStatus.OK);
//    }
//

    @PostMapping("/pessoa/{idPessoa}")
    public ResponseEntity<EnderecoDTO> adicionarEndereco(@PathVariable(value = "idPessoa") Integer idPessoa,
                                                         @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception {

        log.debug("Criando Endereço");
        EnderecoDTO enderecoCriado = enderecoService.create(idPessoa, enderecoCreateDTO);
        log.debug("Endereco Criado!");

        return new ResponseEntity<>(enderecoCriado, HttpStatus.OK);
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable(value = "idEndereco") Integer idEndereco,
                                                         @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception {

        log.debug("Atualizando Endereco");
        EnderecoDTO enderecoAtualizado = enderecoService.update(idEndereco, enderecoCreateDTO);
        log.debug("Endereco Atualizado!");

        return new ResponseEntity<>(enderecoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("{idEndereco}")
    public ResponseEntity<Void> delete(@PathVariable(value = "idEndereco") Integer idEndereco) throws Exception {

        log.debug("Removendo endereco");
        enderecoService.delete(idEndereco);
        log.debug("Endereco Removido!");

        return ResponseEntity.ok().build();
    }

}
