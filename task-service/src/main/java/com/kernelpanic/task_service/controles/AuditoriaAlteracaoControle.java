package com.kernelpanic.task_service.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kernelpanic.task_service.dtos.AuditoriaAlteracaoDTO;
import com.kernelpanic.task_service.servicos.AuditoriaAlteracaoService;

@RestController
@RequestMapping("/tarefas")
public class AuditoriaAlteracaoControle {

    @Autowired
    private AuditoriaAlteracaoService servico;

    @GetMapping("/alteracoes")
    public ResponseEntity<List<AuditoriaAlteracaoDTO>> buscarAlteracoes(
            @RequestParam(required = false) String dataInicio,
            @RequestParam(required = false) String dataFim,
            @RequestParam(required = false) Integer projetoId,
            @RequestParam(required = false) String prioridade) {

        List<AuditoriaAlteracaoDTO> alteracoes = servico.buscarAlteracoes(
            dataInicio,
            dataFim,
            projetoId,
            prioridade
        );

        return ResponseEntity.ok(alteracoes);
    }

    @GetMapping("/{tarefaId}/alteracoes")
    public ResponseEntity<List<AuditoriaAlteracaoDTO>> buscarAlteracoesPorTarefa(
            @PathVariable Integer tarefaId) {

        List<AuditoriaAlteracaoDTO> alteracoes = servico.buscarAlteracoesPorTarefa(tarefaId);
        return ResponseEntity.ok(alteracoes);
    }
}
