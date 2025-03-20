package com.nuvem.gestor.domain.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExameDTO {

    private Long prontuarioId;

    private String tipoExame;

    private LocalDate data;

    private LocalTime hora;

}
