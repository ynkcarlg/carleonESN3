package com.example.universidadeESN3.controller;

import com.example.universidadeESN3.entity.Professor;
import com.example.universidadeESN3.service.ProfessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/professor")
@Slf4j
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<Professor>> buscarTodos() {
        return ResponseEntity.ok(professorService.buscarTodos());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable Long id){

        Professor response = professorService.buscarPorId(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/nome/{nome}")
    public ResponseEntity<List<Professor>> buscarPorNome(@PathVariable String nome){

        List<Professor> response = professorService.buscarPorNome(nome);
        if (response != null && !response.isEmpty()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping
    public ResponseEntity<Professor> salvarProfessor(@RequestBody Professor professor){
        log.info("salvarProfessor() - professor:{}", professor );
        return ResponseEntity.ok(professorService.salvar(professor));
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Professor professor){

        Professor response = professorService.buscarPorId(professor.getId());
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        professorService.atualizar(professor);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Professor response = professorService.buscarPorId(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        professorService.excluir(id);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/inactive/{id}")
    public ResponseEntity<?> desativar(@PathVariable Long id) {
        Professor response = professorService.buscarPorId(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        professorService.desativar(response);
        return ResponseEntity.ok(null);
    }

}
