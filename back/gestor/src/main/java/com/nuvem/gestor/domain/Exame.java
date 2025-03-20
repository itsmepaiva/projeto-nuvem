package com.nuvem.gestor.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_exames")
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomePaciente;

    private String tipoExame;

    private LocalDate data;

    private LocalTime hora;

    @ManyToOne
    @JoinColumn(name = "prontuario_id")
    private Prontuario prontuario;
}
