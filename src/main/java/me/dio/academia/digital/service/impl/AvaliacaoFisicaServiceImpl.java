package me.dio.academia.digital.service.impl;

import lombok.AllArgsConstructor;
import me.dio.academia.digital.entity.model.Aluno;
import me.dio.academia.digital.entity.model.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import me.dio.academia.digital.service.IAvaliacaoFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AvaliacaoFisicaServiceImpl implements IAvaliacaoFisicaService {

  private final AvaliacaoFisicaRepository avaliacaoFisicaRepository;
  private final AlunoRepository alunoRepository;

  @Override
  public AvaliacaoFisica create(AvaliacaoFisicaForm form) {
    var avaliacaoFisica = new AvaliacaoFisica();
    var aluno = alunoRepository.findById(form.getAlunoId()).get();

    avaliacaoFisica.setAluno(aluno);
    avaliacaoFisica.setPeso(form.getPeso());
    avaliacaoFisica.setAltura(form.getAltura());

    return avaliacaoFisicaRepository.save(avaliacaoFisica);
  }

  @Override
  public AvaliacaoFisica get(Long id) {
    return avaliacaoFisicaRepository.findById(id).get();
  }

  @Override
  public List<AvaliacaoFisica> getAll() {
    return avaliacaoFisicaRepository.findAll();
  }

  @Override
  public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm formUpdate) {
    var avaliacao = avaliacaoFisicaRepository.findById(id).get();
    if(formUpdate.getPeso() > 0){
      avaliacao.setPeso(formUpdate.getPeso());
    }
    if (formUpdate.getAltura() > 0){
      avaliacao.setAltura(formUpdate.getAltura());
    }

    return avaliacaoFisicaRepository.saveAndFlush(avaliacao);
  }

  @Override
  public void delete(Long id) {
    avaliacaoFisicaRepository.deleteById(id);
  }
}
