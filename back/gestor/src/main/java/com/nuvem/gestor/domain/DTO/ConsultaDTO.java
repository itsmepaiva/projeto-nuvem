package com.nuvem.gestor.domain.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaDTO {
    private Long id = null;
    
    private LocalDate data;

    private LocalTime horario;

    private boolean presencial;

    private String nomePaciente;

    private String nomeMedico;
}
