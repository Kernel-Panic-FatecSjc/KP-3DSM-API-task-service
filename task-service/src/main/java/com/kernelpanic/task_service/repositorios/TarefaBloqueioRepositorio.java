package com.kernelpanic.task_service.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kernelpanic.task_service.entidades.BloqueioTarefa;

public interface TarefaBloqueioRepositorio extends JpaRepository<BloqueioTarefa, Integer> {

    List<BloqueioTarefa> findByTarefaId(Integer tarefaId);

    // Tarefa com bloqueio ativo
    @Query("SELECT b FROM BloqueioTarefa b WHERE b.tarefa.id = :tarefaId AND b.dataFim IS NULL")
    Optional<BloqueioTarefa> findActiveBlock(@Param("tarefaId") Integer tarefaId);
}