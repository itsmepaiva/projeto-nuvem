package com.nuvem.gestor.domain.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExameGetDTO {
    private Long id;

    private String nomePaciente;

    private String tipoExame;

    private LocalDate data;

    private LocalTime hora;
}
