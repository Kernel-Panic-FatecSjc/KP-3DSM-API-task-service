package com.kernelpanic.task_service.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kernelpanic.task_service.servicos.BloquearTarefaService;

@RestController
@RequestMapping("/tarefas")
public class TarefaBloqueioControle {
    @Autowired
    private BloquearTarefaService bloquearTarefaService;

    @PostMapping("/{id}/bloquear")
    public ResponseEntity<?> bloquear(
            @PathVariable Integer id,
            @RequestParam Integer usuarioId,
            @RequestParam String categoria,
            @RequestParam String descricao) {

        bloquearTarefaService.bloquearTarefa(id, usuarioId, categoria, descricao);

        return ResponseEntity.ok("Tarefa bloqueada com sucesso");
    }
}
