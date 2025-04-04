package com.nuvem.gestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nuvem.gestor.domain.Paciente;


@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findByNome(String nome);
}
