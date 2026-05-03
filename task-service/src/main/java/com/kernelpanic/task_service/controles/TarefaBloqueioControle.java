package com.kernelpanic.task_service.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kernelpanic.task_service.dtos.BloquearTarefaDTO;
import com.kernelpanic.task_service.dtos.DesbloquearTarefaDTO;
import com.kernelpanic.task_service.servicos.BloquearTarefaService;
import com.kernelpanic.task_service.servicos.DesbloquearTarefaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefas")
public class TarefaBloqueioControle {

    @Autowired
    private BloquearTarefaService bloquearTarefaService;

    @Autowired
    private DesbloquearTarefaService desbloquearTarefaService;

    @PatchMapping("/{id}/bloquear")
    public ResponseEntity<?> bloquear(
            @PathVariable Integer id,
            @RequestBody BloquearTarefaDTO dto) {

        try {
            bloquearTarefaService.bloquearTarefa(
                id, dto.usuarioId, dto.categoria, dto.descricao
            );

            return ResponseEntity.ok("Tarefa bloqueada com sucesso");

        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/desbloquear")
    public ResponseEntity<?> desbloquear(
            @PathVariable Integer id,
            @RequestBody DesbloquearTarefaDTO dto) {

        try {
            desbloquearTarefaService.desbloquearTarefa(id, dto.usuarioId);

            return ResponseEntity.ok("Tarefa desbloqueada com sucesso");

        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}