package com.nuvem.gestor.domain.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProntuarioDTO {
    private List<String> sintomas;

    private String diagnostico;

    private boolean precisaExame;

    private Long consultorioId;
}
