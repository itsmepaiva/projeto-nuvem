package com.nuvem.gestor.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MedicoDTO {
    private String nome;

    private String crm;

    private String especialidade;
}
