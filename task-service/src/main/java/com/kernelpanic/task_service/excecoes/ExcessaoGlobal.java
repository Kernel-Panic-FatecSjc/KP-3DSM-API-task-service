package com.kernelpanic.task_service.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

import com.kernelpanic.task_service.dtos.PadraoErroDTO;

@RestControllerAdvice
public class ExcessaoGlobal {
    
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<PadraoErroDTO> tratarEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex) {
        PadraoErroDTO erro = new PadraoErroDTO(
            String.valueOf(HttpStatus.NOT_FOUND.value()), 
            ex.getMessage(),
            ex.getDescricao(),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<PadraoErroDTO> tratarValidacao(MethodArgumentNotValidException ex){
        StringBuilder descricao = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            descricao.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
        });

        PadraoErroDTO erro = new PadraoErroDTO(
            String.valueOf(HttpStatus.BAD_REQUEST.value()), 
            "Erro de validação nos dados enviados",
            descricao.toString(),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}