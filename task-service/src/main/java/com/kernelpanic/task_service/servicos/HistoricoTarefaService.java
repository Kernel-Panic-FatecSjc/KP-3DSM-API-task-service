package com.kernelpanic.task_service.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kernelpanic.task_service.dtos.HistoricoTarefaDTO;
import com.kernelpanic.task_service.entidades.HistoricoTarefa;
import com.kernelpanic.task_service.repositorios.HistoricoTarefaRepositorio;

@Service
public class HistoricoTarefaService {

    @Autowired
    private HistoricoTarefaRepositorio repositorio;

    public List<HistoricoTarefaDTO> buscarTodos() {

        List<HistoricoTarefa> historicos =
            repositorio.findAll();

        return historicos.stream()
            .map(this::converterDTO)
            .toList();
    }

    public List<HistoricoTarefaDTO> buscarPorTarefa(Integer tarefaId) {

        List<HistoricoTarefa> historicos =
            repositorio.findByTarefaIdOrderByDataEventoAsc(tarefaId);

        return historicos.stream()
            .map(this::converterDTO)
            .toList();
    }

    private HistoricoTarefaDTO converterDTO(
            HistoricoTarefa h) {

        return new HistoricoTarefaDTO(
            h.getId(),

            h.getTarefa().getId(),

            h.getUsuarioId(),

            h.getTempoAlocado(),

            h.getBloqueio() != null
                ? h.getBloqueio().getId()
                : null,

            h.getCategoriaImpedimento(),

            h.getTempoBloqueio(),

            h.getDataEvento()
        );
    }
}