package com.kernelpanic.task_service.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuditoriaAlteracaoDTO {

    @NotNull(message = "O ID da alteração é obrigatório")
    private Long id;

    @NotNull(message = "O ID da tarefa é obrigatório")
    private Integer tarefaId;

    private String tarefaNome;

    private String campoAlterado;

    private String valorAnterior;

    private String valorNovo;

    @NotNull(message = "A data da alteração é obrigatória")
    private LocalDateTime dataAlteracao;

    private Long usuarioId;

    private String usuarioNome;

    private Integer projetoId;

    private String prioridade;
}
