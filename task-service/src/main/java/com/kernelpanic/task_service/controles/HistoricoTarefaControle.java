package com.kernelpanic.task_service.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kernelpanic.task_service.dtos.HistoricoTarefaDTO;
import com.kernelpanic.task_service.servicos.HistoricoTarefaService;

@RestController
@RequestMapping("/historico")
@CrossOrigin("*")
public class HistoricoTarefaControle {

    @Autowired
    private HistoricoTarefaService service;

    @GetMapping("/tarefa/{id}")
    public List<HistoricoTarefaDTO> buscarPorTarefa(
            @PathVariable Long id) {

        return service.buscarPorTarefa(id);
    }
}