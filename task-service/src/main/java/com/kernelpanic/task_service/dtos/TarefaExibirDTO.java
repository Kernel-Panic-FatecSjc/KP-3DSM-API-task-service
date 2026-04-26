package com.kernelpanic.task_service.dtos;

import java.sql.Timestamp;
import java.util.List;

import com.kernelpanic.task_service.enums.StatusTarefa;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TarefaExibirDTO {
    
    private Integer id;
    private String nome;
    private String descricao;
    private StatusTarefa status; 
    private List<Integer> idResponsaveis;
    private Integer idProjeto;
    private Timestamp dataCriacao;
}