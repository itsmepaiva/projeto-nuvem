package com.nuvem.gestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nuvem.gestor.domain.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

}
