package com.kernelpanic.task_service.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/tarefas")
public class TarefaBloqueioControle {
    @Autowired
    private BloquearTarefaService bloquearTarefaService;

    @Autowired
    private DesbloquearTarefaService desbloquearTarefaService;

    @PostMapping("/{id}/bloquear")
    public ResponseEntity<?> bloquear(
            @PathVariable Integer id,
            @RequestBody BloquearTarefaDTO dto) {

        bloquearTarefaService.bloquearTarefa(id, dto.usuarioId, dto.categoria, dto.descricao);

        return ResponseEntity.ok("Tarefa bloqueada com sucesso");
    }

    @PostMapping("/{id}/desbloquear")
        public ResponseEntity<?> desbloquear(
        @PathVariable Integer id,
        @RequestBody DesbloquearTarefaDTO dto) {

        desbloquearTarefaService.desbloquearTarefa(id, dto.usuarioId);

    return ResponseEntity.ok("Tarefa desbloqueada com sucesso");
}
}
