package com.kernelpanic.task_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PadraoErroDTO {
    private String statusHttp;
    private String titulo;
    private String detalhes;
    private LocalDateTime momentoDoErro;
}