package com.kernelpanic.task_service.dtos;

import java.security.Timestamp;
import com.kernelpanic.task_service.entidades.StatusTarefa;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TarefaExibirDTO {
    
    private Integer id;
    private String nome;
    private String descricao;
    private StatusTarefa status; 
    private Integer idResponsavel;
    private Integer idProjeto;
    private Timestamp dataCriacao;
}