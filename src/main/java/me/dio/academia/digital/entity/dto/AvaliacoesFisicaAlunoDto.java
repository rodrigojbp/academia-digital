package me.dio.academia.digital.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class AvaliacoesFisicaAlunoDto {
    private String nome;
    private int idade;
    private List<AvaliacaoFisicaDto> avaliacoesFisica;
}

