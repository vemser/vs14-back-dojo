package com.example.pessoa.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> list(){

    }

}
