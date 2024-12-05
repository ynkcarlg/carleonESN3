package com.example.universidadeESN3.service;

import com.example.universidadeESN3.entity.Aluno;
import com.example.universidadeESN3.entity.Professor;

import java.util.List;

public interface IProfessorService {

    Professor buscarPorId(Long id);

    List<Professor> buscarTodos();

    Professor salvar(Professor professor);

    void atualizar(Professor professor);

    void excluir(Long id);
}
