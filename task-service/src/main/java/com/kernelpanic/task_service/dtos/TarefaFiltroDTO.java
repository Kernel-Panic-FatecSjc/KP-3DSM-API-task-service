package com.kernelpanic.task_service.dtos;

import java.util.List;

import com.kernelpanic.task_service.enums.StatusTarefa;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TarefaFiltroDTO {
    private StatusTarefa statusTarefa;
    private Integer idProjeto;
    private List<Integer> idResponsaveis;
}