package com.example.universidadeESN3.controller;

import com.example.universidadeESN3.entity.Aluno;
import com.example.universidadeESN3.service.AlunoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
@Slf4j
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<Aluno>> buscarTodos() {
        return ResponseEntity.ok(alunoService.buscarTodos());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable Long id){

        Aluno response = alunoService.buscarPorId(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/nome/{nome}")
    public ResponseEntity<List<Aluno>> buscarPorNome(@PathVariable String nome){

        List<Aluno> response = alunoService.buscarPorNome(nome);
        if (response != null && !response.isEmpty()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping
    public ResponseEntity<Aluno> salvarAluno(@RequestBody Aluno aluno){
        log.info("salvarAluno() - aluno:{}", aluno );
        return ResponseEntity.ok(alunoService.salvar(aluno));
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Aluno aluno){

        Aluno response = alunoService.buscarPorId(aluno.getId());
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        alunoService.atualizar(aluno);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Aluno response = alunoService.buscarPorId(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        alunoService.excluir(id);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/inactive/{id}")
    public ResponseEntity<?> desativar(@PathVariable Long id) {
        Aluno response = alunoService.buscarPorId(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        alunoService.desativar(response);
        return ResponseEntity.ok(null);
    }


}
