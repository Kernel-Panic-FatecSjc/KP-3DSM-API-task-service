package com.kernelpanic.task_service.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DesbloquearTarefaDTO {
    @NotNull
    public Integer usuarioId;
}
