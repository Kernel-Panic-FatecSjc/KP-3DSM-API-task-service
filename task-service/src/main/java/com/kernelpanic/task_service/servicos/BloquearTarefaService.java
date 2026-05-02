package com.kernelpanic.task_service.servicos;

import java.sql.Timestamp;
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
public class BloquearTarefaService {

    @Autowired
    private TarefaRepositorio tarefaRepositorio;

    @Autowired
    private TarefaBloqueioRepositorio tarefaBloqueioRepositorio;

    @Autowired
    private HistoricoTarefaRepositorio historicoRepositorio;


    @Transactional
    public void bloquearTarefa(Integer tarefaId, Integer usuarioId, String categoria, String descricao){
        if(categoria == null || categoria.isBlank()){
            throw new RuntimeException("Categoria é obrigatória");


        }

        Tarefa tarefa = tarefaRepositorio.findById(tarefaId)
            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        
        if (tarefa.getStatusTarefa() == StatusTarefa.DONE){
            throw new RuntimeException("Não é possível bloquear uma tarefa finalizada");
        }

        Optional<BloqueioTarefa> bloqueioAtivo = tarefaBloqueioRepositorio.findActiveBlock(tarefaId);

        if(bloqueioAtivo.isPresent()){
            throw new RuntimeException("Tarefa já bloqueada");
        }

        BloqueioTarefa bloqueio = new BloqueioTarefa();
        bloqueio.setTarefa(tarefa);
        bloqueio.setCategoriaImpedimento(categoria);
        bloqueio.setDescricaoImpedimento(descricao);
        bloqueio.setDataInicio(LocalDateTime.now());

        tarefaBloqueioRepositorio.save(bloqueio);


        tarefa.setStatusTarefa(StatusTarefa.BLOCKED);
        tarefa.setDataInicioBloqueio(new Timestamp(System.currentTimeMillis()));

        tarefaRepositorio.save(tarefa);


        HistoricoTarefa historico = new HistoricoTarefa();
        historico.setTarefa(tarefa);
        historico.setUsuarioId(usuarioId);
        historico.setBloqueio(bloqueio);
        historico.setCategoriaImpedimento(categoria);
        historico.setDataEvento(LocalDateTime.now());

        historicoRepositorio.save(historico);



    }
}