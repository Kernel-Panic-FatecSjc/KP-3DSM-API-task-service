package com.kernelpanic.task_service.modelo;

import org.springframework.stereotype.Component;
import com.kernelpanic.task_service.entidades.Tarefa;

@Component
public class TarefaAtualizador {
    
    public void atualizar(Tarefa tarefaAtual, Tarefa atualizacao) {
        
        if (atualizacao.getNome() != null && !atualizacao.getNome().isBlank()){
            tarefaAtual.setNome(atualizacao.getNome());
        }
        
        if (atualizacao.getDescricao() != null && !atualizacao.getDescricao().isBlank()){
            tarefaAtual.setDescricao(atualizacao.getDescricao());
        }
        
        if (atualizacao.getStatusTarefa() != null){
            tarefaAtual.setStatusTarefa(atualizacao.getStatusTarefa());
        }

    }
}