package com.kernelpanic.task_service.dtos;

import java.sql.Timestamp;

import com.kernelpanic.task_service.enums.StatusTarefa;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErroRespostaDTO {

    @NotBlank(message = "O nome da tarefa é obrigatório")
    private String nome;
    
    private String descricao;
    
    @NotNull(message = "O status da tarefa é obrigatório")
    private StatusTarefa statusTarefa;
    
    @NotNull(message = "A data de criação da tarefa é obrigatória")
    private Timestamp dataCriacao;

    public ErroRespostaDTO(String nome, String descricao, StatusTarefa statusTarefa, Timestamp dataCriacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.statusTarefa = statusTarefa;
        this.dataCriacao = dataCriacao;
    }
}