package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.dto.AvaliacoesFisicaAlunoDto;
import me.dio.academia.digital.entity.model.Aluno;
import me.dio.academia.digital.entity.model.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

  @Autowired
  private AlunoServiceImpl service;

  @PostMapping
  public Aluno create(@Valid @RequestBody AlunoForm form) {
    return service.create(form);
  }

  @GetMapping("/avaliacoes/{id}")
  public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(@PathVariable Long id) {
    return service.getAllAvaliacaoFisicaId(id);
  }

  @GetMapping("/avaliacoesResumida/{id}")
  public AvaliacoesFisicaAlunoDto getAllAvaliacaoFisicaIdResumida(@PathVariable Long id) {
    return service.getAllAvaliacaoFisicaIdResumida(id);
  }

  @GetMapping
  public List<Aluno> getAll(@RequestParam(value = "dataDeNascimento", required = false)
                                  String dataDeNacimento){
    return service.getAll(dataDeNacimento);
  }

  @DeleteMapping("/deletar/{id}")
  public String deletarAluno(@PathVariable Long id){
    return service.deleteCustomizado(id);
  }

  @GetMapping("/{id}")
  public Aluno buscarAlunoPorId(@PathVariable Long id){
    return service.get(id);
  }

}
