package com.kernelpanic.task_service.controles;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kernelpanic.task_service.dtos.TarefaAtualizarDTO;
import com.kernelpanic.task_service.dtos.TarefaCadastroDTO;
import com.kernelpanic.task_service.dtos.TarefaExibirDTO;
import com.kernelpanic.task_service.enums.StatusTarefa;
import com.kernelpanic.task_service.servicos.TarefaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefas")
public class TarefaControle {

    @Autowired
    private TarefaService service;

    @GetMapping
    public ResponseEntity<List<TarefaExibirDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @PostMapping
    public ResponseEntity<TarefaExibirDTO> criar(@Valid @RequestBody TarefaCadastroDTO dto) {
        TarefaExibirDTO criada = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TarefaExibirDTO> atualizar(@PathVariable Integer id, @Valid @RequestBody TarefaAtualizarDTO dto) {
        TarefaExibirDTO atualizada = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/projeto/{idProjeto}")
    public ResponseEntity<List<TarefaExibirDTO>> listarPorProjeto(@PathVariable Integer idProjeto) {
        return ResponseEntity.ok(service.buscarPorProjeto(idProjeto));
    }

    @GetMapping("/funcionario/{idResponsavel}")
    public ResponseEntity<List<TarefaExibirDTO>> listarPorFuncionario(@PathVariable Integer idResponsavel) {
        return ResponseEntity.ok(service.buscarPorFuncionario(idResponsavel));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TarefaExibirDTO>> listarPorEstado(@PathVariable StatusTarefa status) {
        return ResponseEntity.ok(service.buscarPorEstado(status));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<TarefaExibirDTO>> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        
        Timestamp dataInicio = Timestamp.valueOf(inicio);
        Timestamp dataFim = Timestamp.valueOf(fim);
        
        return ResponseEntity.ok(service.buscarPorPeriodo(dataInicio, dataFim));



        
    }

    @GetMapping("/resumo-por-projeto")
    public ResponseEntity<?> resumoPorProjeto() {
        return ResponseEntity.ok(service.resumoPorProjeto());
    }

    
    }