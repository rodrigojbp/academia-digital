package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.dto.AvaliacoesFisicaAlunoDto;
import me.dio.academia.digital.entity.dto.AvaliacaoFisicaDto;
import me.dio.academia.digital.entity.model.Aluno;
import me.dio.academia.digital.entity.model.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.infra.utils.JavaTimeUtils;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.service.IAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoServiceImpl implements IAlunoService {

  @Autowired
  private AlunoRepository alunoRepository;

  @Override
  public Aluno create(AlunoForm form) {
    Aluno aluno = new Aluno();
    aluno.setNome(form.getNome());
    aluno.setCpf(form.getCpf());
    aluno.setBairro(form.getBairro());
    aluno.setDataDeNascimento(form.getDataDeNascimento());

    return alunoRepository.save(aluno);
  }

  @Override
  public Aluno get(Long id) {
    return alunoRepository.findById(id).get();
  }

  @Override
  public List<Aluno> getAll(String dataDeNascimento) {

    if(dataDeNascimento == null) {
      return alunoRepository.findAll();
    } else {
      LocalDate localDate = LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
      return alunoRepository.findByDataDeNascimento(localDate);
    }

  }

  @Override
  public Aluno update(Long id, AlunoUpdateForm formUpdate) {
    var aluno = alunoRepository.findById(id).get();

    if(!(formUpdate.getNome() == null || formUpdate.getNome().isBlank() || formUpdate.getNome().isEmpty())){
      aluno.setNome(formUpdate.getNome());
    }
    if(!(formUpdate.getBairro() == null || formUpdate.getBairro().isBlank() || formUpdate.getBairro().isEmpty())){
      aluno.setBairro(formUpdate.getBairro());
    }
    if (formUpdate.getDataDeNascimento() != null){
      aluno.setDataDeNascimento(formUpdate.getDataDeNascimento());
    }

    return alunoRepository.saveAndFlush(aluno);
  }

  @Override
  public void delete(Long id) {
    alunoRepository.deleteById(id);
  }

  public String deleteCustomizado(Long id) {
    var aluno = alunoRepository.findById(id).get();
    alunoRepository.deleteById(id);
    String msg = "Registro do aluno: " + aluno.getNome() + " foi removido!";
    return msg;
  }

  @Override
  public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(Long id) {
    Aluno aluno = alunoRepository.findById(id).get();
    return aluno.getAvaliacoes();
  }

  public AvaliacoesFisicaAlunoDto getAllAvaliacaoFisicaIdResumida(Long id) {
    var aluno = alunoRepository.findById(id).get();

    var avaliacoesFisicaAlunoDto = new AvaliacoesFisicaAlunoDto();
    avaliacoesFisicaAlunoDto.setNome(aluno.getNome());
    avaliacoesFisicaAlunoDto.setIdade(calcularIdade(aluno.getDataDeNascimento()));

    var avaliacoes = aluno.getAvaliacoes();
    List<AvaliacaoFisicaDto> avaliacaoFisicaDtoList = new ArrayList<>();

    for (AvaliacaoFisica avaliacao: avaliacoes){
      AvaliacaoFisicaDto avaliacaoFisicaDto = new AvaliacaoFisicaDto();
      avaliacaoFisicaDto.setDataAvaliacao(avaliacao.getDataDaAvaliacao().toLocalDate());
      avaliacaoFisicaDto.setPeso(avaliacao.getPeso());
      avaliacaoFisicaDto.setAltura(avaliacao.getAltura());
      avaliacaoFisicaDtoList.add(avaliacaoFisicaDto);
    }
    avaliacoesFisicaAlunoDto.setAvaliacoesFisica(avaliacaoFisicaDtoList);

    return avaliacoesFisicaAlunoDto;
  }
  private int calcularIdade(LocalDate dataNascimento){
    final LocalDate dataAtual = LocalDate.now();
    final Period periodo = Period.between(dataNascimento, dataAtual);
    return periodo.getYears();
  }
}
