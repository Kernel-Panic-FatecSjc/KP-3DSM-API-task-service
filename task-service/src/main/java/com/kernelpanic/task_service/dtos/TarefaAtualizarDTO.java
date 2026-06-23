package com.kernelpanic.task_service.dtos;

import java.util.List;

import com.kernelpanic.task_service.enums.StatusTarefa;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TarefaAtualizarDTO {

    @Size(max = 255, message = "O nome deve ter no máximo 255 caracteres")
    private String nome;

    @Size(max = 1000, message = "A descrição não pode passar de 1000 caracteres")
    private String descricao;

    private List<Integer> idResponsaveis;

    private Integer idProjeto;

    private StatusTarefa statusTarefa;

    private Long usuarioId;

    private String usuarioNome;
}