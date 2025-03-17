package com.nuvem.gestor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nuvem.gestor.domain.Medico;


@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{
    Medico findByNome(String nome);
}
