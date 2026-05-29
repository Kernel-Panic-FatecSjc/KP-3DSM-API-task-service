package com.kernelpanic.task_service.repositorios;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kernelpanic.task_service.enums.StatusTarefa;
import com.kernelpanic.task_service.entidades.Tarefa;

public interface TarefaRepositorio extends JpaRepository<Tarefa, Integer> {
    
    List<Tarefa> findByIdProjeto(Integer idProjeto);
    List<Tarefa> findByStatusTarefa(StatusTarefa status);
    List<Tarefa> findByDataCriacaoBetween(Timestamp inicio, Timestamp fim);

    @Query(value = "SELECT * FROM tarefas WHERE FIND_IN_SET(:idFuncionario, ids_responsaveis) > 0", nativeQuery = true)
    List<Tarefa> buscarPorResponsavelNaString(@Param("idFuncionario") Integer idFuncionario);

    @Query("""
            SELECT t.idProjeto, t.statusTarefa, COUNT(t) 
            FROM Tarefa t 
            GROUP BY t.idProjeto, t.statusTarefa
    """)
    List<Object[]> resumoPorProjeto();
}