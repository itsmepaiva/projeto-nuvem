package com.nuvem.gestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nuvem.gestor.domain.Exame;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Long>{

}
