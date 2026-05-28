package com.kernelpanic.task_service.entidades;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auditoria_alteracoes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditoriaAlteracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tarefa_id", nullable = false)
    private Integer tarefaId;

    @Column(name = "tarefa_nome", nullable = false)
    private String tarefaNome;

    @Column(name = "campo_alterado", nullable = false)
    private String campoAlterado;

    @Column(name = "valor_anterior", columnDefinition = "TEXT")
    private String valorAnterior;

    @Column(name = "valor_novo", columnDefinition = "TEXT")
    private String valorNovo;

    @Column(name = "data_alteracao", nullable = false)
    private LocalDateTime dataAlteracao;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "usuario_nome")
    private String usuarioNome;

    @Column(name = "projeto_id")
    private Integer projetoId;

    @Column(name = "prioridade")
    private String prioridade;
}
