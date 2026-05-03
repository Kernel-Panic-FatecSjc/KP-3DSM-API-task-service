package com.kernelpanic.task_service.dtos;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class TarefaResponseDTO {
    private Integer id;
    private String nome;
    private String descricao;

    private Integer projetoId;
    private String nomeProjeto;

    private String status;

    private boolean bloqueada;

    private Timestamp dataCriacao;
    private List<Integer> idResponsaveis;
}