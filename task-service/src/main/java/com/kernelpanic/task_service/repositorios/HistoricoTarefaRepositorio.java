package com.kernelpanic.task_service.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kernelpanic.task_service.entidades.HistoricoTarefa;

@Repository
public interface HistoricoTarefaRepositorio extends JpaRepository<HistoricoTarefa, Long> {

    List<HistoricoTarefa> findByTarefaId(Long tarefaId);

}
