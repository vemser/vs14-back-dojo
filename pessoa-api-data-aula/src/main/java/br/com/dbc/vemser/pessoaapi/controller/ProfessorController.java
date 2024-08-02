package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dto.ProfessorCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.ProfessorDTO;
import br.com.dbc.vemser.pessoaapi.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> findAll() {
        return new ResponseEntity<>(professorService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/universidade/{idUniversidade}")
    public ResponseEntity<ProfessorDTO> create(@RequestBody ProfessorCreateDTO ProfessorCreateDTO, @PathVariable Integer idUniversidade) {
        return new ResponseEntity<>(professorService.create(ProfessorCreateDTO, idUniversidade), HttpStatus.CREATED);
    }
}
