package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dto.PetCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.PetDTO;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.PetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pet")
@RequiredArgsConstructor
@Tag(name = "Pet", description = "Endpoints para pets")
public class PetController {

    private final PetService petService;

    @GetMapping
    public ResponseEntity<List<PetDTO>> findAll() {
        return new ResponseEntity<>(petService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/pessoa/{idPessoa}")
    public ResponseEntity<PetDTO> create(@PathVariable Integer idPessoa, @Valid @RequestBody PetCreateDTO petCreateDTO) throws RegraDeNegocioException {
        return new ResponseEntity<>(petService.create(idPessoa, petCreateDTO), HttpStatus.CREATED);
    }
}
