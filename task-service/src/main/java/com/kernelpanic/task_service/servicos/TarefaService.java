package com.kernelpanic.task_service.servicos;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kernelpanic.task_service.dtos.ResumoProjetoDTO;
import com.kernelpanic.task_service.dtos.TarefaAtualizarDTO;
import com.kernelpanic.task_service.dtos.TarefaCadastroDTO;
import com.kernelpanic.task_service.dtos.TarefaExibirDTO;
import com.kernelpanic.task_service.dtos.TarefaResponseDTO;
import com.kernelpanic.task_service.enums.StatusTarefa;
import com.kernelpanic.task_service.entidades.Tarefa;
import com.kernelpanic.task_service.excecoes.EntidadeNaoEncontradaException;
import com.kernelpanic.task_service.repositorios.TarefaRepositorio;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class TarefaService {
    
    @Autowired
    private TarefaRepositorio repositorio;

    @Autowired
    private AuditoriaAlteracaoService auditoriaService;

    public TarefaExibirDTO criar(TarefaCadastroDTO dto) {
        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setNome(dto.getNome());
        novaTarefa.setDescricao(dto.getDescricao());
        novaTarefa.setIdResponsaveis(dto.getIdResponsaveis());
        novaTarefa.setIdProjeto(dto.getIdProjeto());
        novaTarefa.setStatusTarefa(dto.getStatusTarefa());
        novaTarefa.setDataCriacao(new Timestamp(System.currentTimeMillis()));

        Tarefa salva = repositorio.save(novaTarefa);
        return converterParaDTO(salva);
    }

    public TarefaExibirDTO atualizar(Integer id, TarefaAtualizarDTO dto) {
        Tarefa tarefa = repositorio.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                    "Tarefa Inexistente", 
                    "Não foi possível atualizar. A tarefa de ID " + id + " não consta no banco de dados."
                ));

        // Capturar valores antigos antes da atualização
        String nomeAntigo = tarefa.getNome();
        String descricaoAntiga = tarefa.getDescricao();
        String statusAntigo = tarefa.getStatusTarefa() != null ? tarefa.getStatusTarefa().name() : null;
        String projetoAntigo = tarefa.getIdProjeto() != null ? tarefa.getIdProjeto().toString() : null;
        String responsaveisAntigo = tarefa.getIdResponsaveis() != null ? tarefa.getIdResponsaveis().toString() : null;

        if (dto.getNome() != null) tarefa.setNome(dto.getNome());
        if (dto.getDescricao() != null) tarefa.setDescricao(dto.getDescricao());
        if (dto.getIdResponsaveis() != null) tarefa.setIdResponsaveis(dto.getIdResponsaveis());
        if (dto.getIdProjeto() != null) tarefa.setIdProjeto(dto.getIdProjeto());
        if (dto.getStatusTarefa() != null) tarefa.setStatusTarefa(dto.getStatusTarefa());

        Tarefa atualizada = repositorio.save(tarefa);

        // Registrar alterações na auditoria
        Integer projetoId = tarefa.getIdProjeto();
        String tarefaNome = tarefa.getNome();
        Long uid = dto.getUsuarioId();
        String uNome = dto.getUsuarioNome();

        if (dto.getNome() != null && !Objects.equals(dto.getNome(), nomeAntigo)) {
            auditoriaService.registrarAlteracao(id, tarefaNome, "nome", nomeAntigo, dto.getNome(), uid, uNome, projetoId, null);
        }
        if (dto.getDescricao() != null && !Objects.equals(dto.getDescricao(), descricaoAntiga)) {
            auditoriaService.registrarAlteracao(id, tarefaNome, "descricao", descricaoAntiga, dto.getDescricao(), uid, uNome, projetoId, null);
        }
        if (dto.getStatusTarefa() != null && !Objects.equals(dto.getStatusTarefa().name(), statusAntigo)) {
            auditoriaService.registrarAlteracao(id, tarefaNome, "statusTarefa", statusAntigo, dto.getStatusTarefa().name(), uid, uNome, projetoId, null);
        }
        if (dto.getIdProjeto() != null && !Objects.equals(dto.getIdProjeto().toString(), projetoAntigo)) {
            auditoriaService.registrarAlteracao(id, tarefaNome, "idProjeto", projetoAntigo, dto.getIdProjeto().toString(), uid, uNome, projetoId, null);
        }
        if (dto.getIdResponsaveis() != null && !Objects.equals(dto.getIdResponsaveis().toString(), responsaveisAntigo)) {
            auditoriaService.registrarAlteracao(id, tarefaNome, "idResponsaveis", responsaveisAntigo, dto.getIdResponsaveis().toString(), uid, uNome, projetoId, null);
        }

        return converterParaDTO(atualizada);
    }

    public void deletar(Integer id) {
        Tarefa tarefa = repositorio.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                    "Falha ao Deletar", 
                    "A tarefa de ID " + id + " não foi encontrada."
                ));
        repositorio.delete(tarefa); 
    }

    public List<TarefaResponseDTO> listarTodas() {
    return repositorio.findAll()
        .stream()
        .map(this::converter)
        .toList();
}

    public List<TarefaExibirDTO> buscarPorProjeto(Integer idProjeto) {
        List<Tarefa> tarefas = repositorio.findByIdProjeto(idProjeto);
        return tarefas.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    public List<TarefaExibirDTO> buscarPorFuncionario(Integer idResponsavel) {
        List<Tarefa> tarefas = repositorio.buscarPorResponsavelNaString(idResponsavel);
        return tarefas.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    public List<TarefaExibirDTO> buscarPorEstado(StatusTarefa status) {
        List<Tarefa> tarefas = repositorio.findByStatusTarefa(status);
        return tarefas.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    public List<TarefaExibirDTO> buscarPorPeriodo(Timestamp inicio, Timestamp fim) {
        List<Tarefa> tarefas = repositorio.findByDataCriacaoBetween(inicio, fim);
        return tarefas.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    private TarefaExibirDTO converterParaDTO(Tarefa tarefa) {
        TarefaExibirDTO exibicao = new TarefaExibirDTO();
        exibicao.setId(tarefa.getId());
        exibicao.setNome(tarefa.getNome());
        exibicao.setDescricao(tarefa.getDescricao());
        exibicao.setStatus(tarefa.getStatusTarefa());
        exibicao.setIdResponsaveis(tarefa.getIdResponsaveis());
        exibicao.setIdProjeto(tarefa.getIdProjeto());
        exibicao.setDataCriacao(tarefa.getDataCriacao());
        exibicao.setBloqueada(tarefa.getStatusTarefa() == StatusTarefa.BLOCKED);
        return exibicao;
    }

    private TarefaResponseDTO converter(Tarefa tarefa) {
    TarefaResponseDTO dto = new TarefaResponseDTO();

    dto.setId(tarefa.getId());
    dto.setNome(tarefa.getNome());
    dto.setDescricao(tarefa.getDescricao());
    dto.setProjetoId(tarefa.getIdProjeto());
    dto.setNomeProjeto("Projeto " + tarefa.getIdProjeto()); // temporário
    dto.setDataCriacao(tarefa.getDataCriacao());
    dto.setIdResponsaveis(tarefa.getIdResponsaveis());

    
    switch (tarefa.getStatusTarefa()) {
        case TO_DO -> dto.setStatus("To Do");
        case DOING -> dto.setStatus("Doing");
        case DONE -> dto.setStatus("Done");
        case BLOCKED -> dto.setStatus("Doing");
    }

    dto.setBloqueada(tarefa.getStatusTarefa() == StatusTarefa.BLOCKED);

    return dto;
}


    public List<ResumoProjetoDTO> resumoPorProjeto(){
        List<Object[]> dados = repositorio.resumoPorProjeto();
        Map<Integer, Map<String, Long>> agrupado = new HashMap<>();

        for (Object[] row: dados){
            Integer projectId = (Integer) row[0];
            StatusTarefa status = (StatusTarefa) row[1];
            Long count = (Long) row[2];


            agrupado
                .computeIfAbsent(projectId, k -> new HashMap<>())
                .put(status.name(), count);
        }

        return agrupado.entrySet().stream()
            .map(entry -> new ResumoProjetoDTO(entry.getKey(), entry.getValue()))
            .toList();

    }
}