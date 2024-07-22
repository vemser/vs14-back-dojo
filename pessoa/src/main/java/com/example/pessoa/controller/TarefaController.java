package com.example.pessoa.controller;


import com.example.pessoa.dto.TarefaDTO;
import com.example.pessoa.entity.Tarefas;
import com.example.pessoa.repository.TarefasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarefa")
public class TarefaController {

    private final TarefasRepository tarefasRepository;

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> list() throws Exception {
        tarefasRepository.adicionar(new Tarefas());

        return null;
    }

}
