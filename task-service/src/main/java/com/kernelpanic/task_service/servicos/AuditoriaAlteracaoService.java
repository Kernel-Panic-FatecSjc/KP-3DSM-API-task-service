package com.kernelpanic.task_service.servicos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kernelpanic.task_service.dtos.AuditoriaAlteracaoDTO;
import com.kernelpanic.task_service.entidades.AuditoriaAlteracao;
import com.kernelpanic.task_service.repositorios.AuditoriaAlteracaoRepositorio;

@Service
public class AuditoriaAlteracaoService {

    @Autowired
    private AuditoriaAlteracaoRepositorio repositorio;

    @Transactional(readOnly = true)
    public List<AuditoriaAlteracaoDTO> buscarAlteracoes(
            String dataInicio,
            String dataFim,
            Integer projetoId,
            String prioridade,
            Long usuarioId) {

        LocalDateTime inicio = null;
        LocalDateTime fim = null;

        if (dataInicio != null && !dataInicio.isEmpty()) {
            inicio = LocalDateTime.parse(dataInicio + "T00:00:00");
        }

        if (dataFim != null && !dataFim.isEmpty()) {
            fim = LocalDateTime.parse(dataFim + "T23:59:59");
        }

        List<AuditoriaAlteracao> alteracoes = repositorio.findWithFilters(
            inicio,
            fim,
            projetoId,
            prioridade,
            usuarioId
        );

        return alteracoes.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void registrarAlteracao(
            Integer tarefaId,
            String tarefaNome,
            String campoAlterado,
            String valorAnterior,
            String valorNovo,
            Long usuarioId,
            String usuarioNome,
            Integer projetoId,
            String prioridade) {

        AuditoriaAlteracao alteracao = new AuditoriaAlteracao();
        alteracao.setTarefaId(tarefaId);
        alteracao.setTarefaNome(tarefaNome);
        alteracao.setCampoAlterado(campoAlterado);
        alteracao.setValorAnterior(valorAnterior);
        alteracao.setValorNovo(valorNovo);
        alteracao.setDataAlteracao(LocalDateTime.now());
        alteracao.setUsuarioId(usuarioId);
        alteracao.setUsuarioNome(usuarioNome);
        alteracao.setProjetoId(projetoId);
        alteracao.setPrioridade(prioridade);

        repositorio.save(alteracao);
    }

    @Transactional(readOnly = true)
    public List<AuditoriaAlteracaoDTO> buscarAlteracoesPorTarefa(Integer tarefaId) {
        List<AuditoriaAlteracao> alteracoes = repositorio.findByTarefaId(tarefaId);
        return alteracoes.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    private AuditoriaAlteracaoDTO converterParaDTO(AuditoriaAlteracao alteracao) {
        AuditoriaAlteracaoDTO dto = new AuditoriaAlteracaoDTO();
        dto.setId(alteracao.getId());
        dto.setTarefaId(alteracao.getTarefaId());
        dto.setTarefaNome(alteracao.getTarefaNome());
        dto.setCampoAlterado(alteracao.getCampoAlterado());
        dto.setValorAnterior(alteracao.getValorAnterior());
        dto.setValorNovo(alteracao.getValorNovo());
        dto.setDataAlteracao(alteracao.getDataAlteracao());
        dto.setUsuarioId(alteracao.getUsuarioId());
        dto.setUsuarioNome(alteracao.getUsuarioNome());
        dto.setProjetoId(alteracao.getProjetoId());
        dto.setPrioridade(alteracao.getPrioridade());
        return dto;
    }
}
