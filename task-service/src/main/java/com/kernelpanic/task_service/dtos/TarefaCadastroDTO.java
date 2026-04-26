package com.kernelpanic.task_service.dtos;

import java.util.List;

import com.kernelpanic.task_service.enums.StatusTarefa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TarefaCadastroDTO {

    @NotBlank(message = "O nome da tarefa é obrigatório")
    @Size(max = 255, message = "O nome deve ter no máximo 255 caracteres")
    private String nome;

    @Size(max = 1000, message = "A descrição não pode passar de 1000 caracteres")
    private String descricao;

    @NotNull(message = "Os responsáveis são obrigatórios")
    private List<Integer> idResponsaveis;

    @NotNull(message = "O ID do projeto é obrigatório")
    private Integer idProjeto;

    @NotNull(message = "O status inicial da tarefa é obrigatório")
    private StatusTarefa statusTarefa;
}