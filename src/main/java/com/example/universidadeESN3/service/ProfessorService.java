package com.example.universidadeESN3.service;

import com.example.universidadeESN3.entity.Professor;
import com.example.universidadeESN3.repository.ProfessorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProfessorService implements IProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public Professor buscarPorId(Long id) {
        Optional<Professor> response = professorRepository.findById(id);
        if (response.isPresent()) {
            return response.get();
        }
        return null;
    }

    public List<Professor> buscarTodos() {
        return professorRepository.findAll();
    }

    @Override
    public Professor salvar(Professor professor) {
        log.info("salvar() - professor:{}", professor );
        return professorRepository.save(professor);
    }

    @Override
    public void atualizar(Professor professor) {
        log.info("atualizar() - professor:{}", professor );
        professorRepository.save(professor);
    }

    @Override
    public void excluir(Long id) {
        professorRepository.deleteById(id);
    }

    public void desativar(Professor professor) {
        professor.setActive(Boolean.FALSE);
        professorRepository.save(professor);
    }

    public List<Professor> buscarPorNome(String nome) {
        //  return professorRepository.findByNome(nome);
        return professorRepository.findByNomeStartingWithIgnoreCase(nome);
    }

    }

