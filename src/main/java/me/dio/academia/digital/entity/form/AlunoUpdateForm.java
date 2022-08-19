package me.dio.academia.digital.entity.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Data
public class AlunoUpdateForm {

  @Size(min = 3, max = 100, message = "Tamanho minimo é 3 e o máximo  é 100")
  private String nome;

  @Size(min = 3, max = 100, message = "Tamanho minimo é 3 e o máximo  é 100")
  private String bairro;

  @PastOrPresent(message = "A data nao pode estar no futuro!")
  private LocalDate dataDeNascimento;
}
