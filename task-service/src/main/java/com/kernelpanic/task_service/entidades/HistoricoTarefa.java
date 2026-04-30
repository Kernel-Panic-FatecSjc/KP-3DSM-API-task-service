package com.kernelpanic.task_service.entidades;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "historico_tarefa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoTarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tarefa_id", nullable = false)
    private Tarefa tarefa;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "tempo_alocado")
    private Integer tempoAlocado;

    @ManyToOne
    @JoinColumn(name = "bloqueio_id")
    private BloqueioTarefa bloqueio;

    @Column(name = "categoria_impedimento", length = 255)
    private String categoriaImpedimento;

    @Column(name = "tempo_bloqueio")
    private Integer tempoBloqueio;

    @Column(name = "data_evento")
    private LocalDateTime dataEvento;
}