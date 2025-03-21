package com.nuvem.gestor.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {
    private String nome;

    private String cpf;

    private String altura;

    private Double peso;

    private Integer idade;
}
