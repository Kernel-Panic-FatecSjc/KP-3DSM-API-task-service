package com.kernelpanic.task_service.dtos;

import jakarta.validation.constraints.NotNull;

public class BloquearTarefaDTO {

    @NotNull
    public Integer usuarioId;
    public String categoria;
    public String descricao;
}
