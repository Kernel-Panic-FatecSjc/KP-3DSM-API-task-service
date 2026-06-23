package com.kernelpanic.task_service.repositorios;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kernelpanic.task_service.entidades.AuditoriaAlteracao;

@Repository
public interface AuditoriaAlteracaoRepositorio extends JpaRepository<AuditoriaAlteracao, Long> {

    List<AuditoriaAlteracao> findByTarefaId(Integer tarefaId);

    @Query("SELECT a FROM AuditoriaAlteracao a WHERE " +
           "(:dataInicio IS NULL OR a.dataAlteracao >= :dataInicio) AND " +
           "(:dataFim IS NULL OR a.dataAlteracao <= :dataFim) AND " +
           "(:projetoId IS NULL OR a.projetoId = :projetoId) AND " +
           "(:prioridade IS NULL OR a.prioridade = :prioridade) AND " +
           "(:usuarioId IS NULL OR a.usuarioId = :usuarioId) " +
           "ORDER BY a.dataAlteracao DESC")
    List<AuditoriaAlteracao> findWithFilters(
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim,
            @Param("projetoId") Integer projetoId,
            @Param("prioridade") String prioridade,
            @Param("usuarioId") Long usuarioId);
}
