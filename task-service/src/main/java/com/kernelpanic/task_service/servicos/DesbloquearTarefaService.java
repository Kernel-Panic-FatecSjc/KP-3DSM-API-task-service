package com.kernelpanic.task_service.servicos;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kernelpanic.task_service.entidades.BloqueioTarefa;
import com.kernelpanic.task_service.entidades.HistoricoTarefa;
import com.kernelpanic.task_service.entidades.Tarefa;
import com.kernelpanic.task_service.enums.StatusTarefa;
import com.kernelpanic.task_service.repositorios.HistoricoTarefaRepositorio;
import com.kernelpanic.task_service.repositorios.TarefaBloqueioRepositorio;
import com.kernelpanic.task_service.repositorios.TarefaRepositorio;

import jakarta.transaction.Transactional;

@Service
public class DesbloquearTarefaService {

    @Autowired
    private TarefaRepositorio tarefaRepositorio;

    @Autowired
    private TarefaBloqueioRepositorio bloqueioRepositorio;

    @Autowired
    private HistoricoTarefaRepositorio historicoRepositorio;

    @Autowired
    private AuditoriaAlteracaoService auditoriaService;

    @Transactional
    public void desbloquearTarefa(Integer tarefaId, Integer usuarioId) {

        if (usuarioId == null) {
            throw new IllegalArgumentException("Usuário é obrigatório");
        }

        Tarefa tarefa = tarefaRepositorio.findById(tarefaId)
            .orElseThrow(() -> new NoSuchElementException("Tarefa não encontrada"));

        Optional<BloqueioTarefa> bloqueioOpt =
            bloqueioRepositorio.findActiveBlock(tarefaId);

        if (bloqueioOpt.isEmpty()) {
            throw new IllegalStateException("Tarefa não está bloqueada");
        }

        BloqueioTarefa bloqueio = bloqueioOpt.get();

        LocalDateTime agora = LocalDateTime.now();
        bloqueio.setDataFim(agora);

        long minutos =
            Duration.between(bloqueio.getDataInicio(), agora).toMinutes();

        bloqueio.setTempoBloqueio(Math.toIntExact(minutos));

        bloqueioRepositorio.save(bloqueio);

        
        tarefa.setStatusTarefa(StatusTarefa.DOING); 
        tarefa.setDataFimBloqueio(Timestamp.from(Instant.now()));

        tarefaRepositorio.save(tarefa);

        
        HistoricoTarefa historico = new HistoricoTarefa();
        historico.setTarefa(tarefa);
        historico.setUsuarioId(usuarioId);
        historico.setBloqueio(bloqueio);
        historico.setTempoBloqueio(Math.toIntExact(minutos));
        historico.setDataEvento(agora);

        historicoRepositorio.save(historico);

        auditoriaService.registrarAlteracao(
            tarefaId,
            tarefa.getNome(),
            "desbloqueio",
            "BLOCKED",
            "DOING",
            usuarioId.longValue(),
            null,
            tarefa.getIdProjeto(),
            null
        );
    }
}