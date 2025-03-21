package com.nuvem.gestor.domain.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaDTO {
    private LocalDate data;

    private LocalTime horario;

    private boolean ePresencial;

    private String nomePaciente;

    private String nomeMedico;
}
