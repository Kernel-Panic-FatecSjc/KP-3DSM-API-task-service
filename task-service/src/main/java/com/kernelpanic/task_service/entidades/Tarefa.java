package com.kernelpanic.task_service.entidades;

import java.sql.Timestamp;
import java.util.List;

import com.kernelpanic.task_service.enums.StatusTarefa;
import com.kernelpanic.task_service.modelo.ConverterTexto;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column (name = "nome", nullable = false, length = 255)
    private String nome;

    @Column (name = "descricao", nullable = true, length = 1000)
    private String descricao;

    @Convert(converter = ConverterTexto.class)
    @Column(name = "ids_responsaveis")
    private List<Integer> idResponsaveis;

    @Enumerated(EnumType.STRING)
    @Column (name = "status", nullable = false)
    private StatusTarefa statusTarefa;

    @Column (name = "id_projeto", nullable = false)
    private Integer idProjeto;

    @Column (name = "data_criacao", nullable = false)
    private Timestamp dataCriacao;

    @Column (name = "data_inicio_bloqueio")
    private Timestamp dataInicioBloqueio;

    @Column (name = "data_fim_bloqueio")
    private Timestamp dataFimBloqueio;
}
