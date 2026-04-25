package com.kernelpanic.task_service.modelo;

import org.springframework.stereotype.Component;
import com.kernelpanic.task_service.entidades.Tarefa;
import java.util.List;

@Component
public class TarefaSelecionador {
    
    public Tarefa selecionar(List<Tarefa> tarefas, Integer id) {
        return tarefas.stream()
                .filter(tarefa -> tarefa.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}