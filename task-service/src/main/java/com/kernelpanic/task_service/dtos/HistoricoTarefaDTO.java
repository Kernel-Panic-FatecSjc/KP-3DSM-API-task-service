package com.kernelpanic.task_service.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoTarefaDTO {

    private Integer id;

    private Integer tarefaId;

    private Integer usuarioId;

    private Integer tempoAlocado;

    private Integer bloqueioId;

    private String categoriaImpedimento;

    private Integer tempoBloqueio;

    private LocalDateTime dataEvento;
}