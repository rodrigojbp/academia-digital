package me.dio.academia.digital.entity.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Data
public class AvaliacaoFisicaDto {

    private LocalDate dataAvaliacao;
    private double peso;
    private double altura;
}
