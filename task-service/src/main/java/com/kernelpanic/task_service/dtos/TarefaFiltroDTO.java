package com.kernelpanic.task_service.dtos;

import com.kernelpanic.task_service.entidades.StatusTarefa;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TarefaFiltroDTO {
    private StatusTarefa statusTarefa;
    private Integer idProjeto;
    private Integer idResponsavel;
}