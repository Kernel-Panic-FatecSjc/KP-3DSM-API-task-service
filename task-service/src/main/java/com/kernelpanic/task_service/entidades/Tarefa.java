package com.kernelpanic.task_service.entidades;

import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "tarefas")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Tarefa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column (name = "nome", nullable = false, length = 255)
    private String nome;

    @Column (name = "descricao", nullable = true, length = 1000)
    private String descricao;

    @Id
    @Column (name = "idResponsavel", nullable = false)
    private Integer idResponsavel;

    @Enumerated(EnumType.STRING)
    @Column (name = "status", nullable = false)
    private StatusTarefa statusTarefa;

    @Id
    @Column (name = "idProjeto", nullable = false)
    private Integer idProjeto;

    @Column (name = "dataCriacao", nullable = false)
    private Timestamp dataCriacao;
}
