package com.kernelpanic.task_service.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kernelpanic.task_service.entidades.HistoricoTarefa;

@Repository
public interface HistoricoTarefaRepositorio extends JpaRepository<HistoricoTarefa, Integer> {

    @Query("SELECT h FROM HistoricoTarefa h WHERE h.tarefa.id = :tarefaId ORDER BY h.dataEvento ASC")
    List<HistoricoTarefa> findByTarefaIdOrderByDataEventoAsc(@Param("tarefaId") Integer tarefaId);

}
