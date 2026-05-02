package com.kernelpanic.task_service.servicos;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
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

    @Transactional
    public void desbloquearTarefa(Integer tarefaId, Integer usuarioId) {

        Tarefa tarefa = tarefaRepositorio.findById(tarefaId)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        Optional<BloqueioTarefa> bloqueioOpt = bloqueioRepositorio.findActiveBlock(tarefaId);

        if (bloqueioOpt.isEmpty()) {
            throw new RuntimeException("Tarefa não está bloqueada");
        }

        BloqueioTarefa bloqueio = bloqueioOpt.get();

       
        LocalDateTime agora = LocalDateTime.now();
        bloqueio.setDataFim(agora);

   
        long minutos = Duration.between(bloqueio.getDataInicio(), agora).toMinutes();
        bloqueio.setTempoBloqueio((int) minutos);

        bloqueioRepositorio.save(bloqueio);

        
        tarefa.setStatusTarefa(StatusTarefa.DOING);
        tarefa.setDataFimBloqueio(new Timestamp(System.currentTimeMillis()));

        tarefaRepositorio.save(tarefa);

        
        HistoricoTarefa historico = new HistoricoTarefa();
        historico.setTarefa(tarefa);
        historico.setUsuarioId(usuarioId);
        historico.setBloqueio(bloqueio);
        historico.setDataEvento(agora);

        historicoRepositorio.save(historico);
    }
}