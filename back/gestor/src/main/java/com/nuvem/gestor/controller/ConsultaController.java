package com.nuvem.gestor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nuvem.gestor.domain.Consulta;
import com.nuvem.gestor.domain.DTO.ConsultaDTO;
import com.nuvem.gestor.service.ConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public List<Consulta> retornar() {
        return consultaService.listarConsultas();
    }
    
    @PostMapping
    public Consulta gerarConsulta(@RequestBody ConsultaDTO consultaDTO){
        return consultaService.marcarConsulta(consultaDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> atualizar(@RequestParam Long id, @RequestBody ConsultaDTO consultaDTO){
        consultaService.atualizarConsulta(id, consultaDTO);
        return ResponseEntity.ok("Dados da consulta atualizados!");
    }

    @DeleteMapping
    public ResponseEntity<String> deletar(@RequestParam Long id){
        consultaService.excluirConsulta(id);
        return ResponseEntity.ok("Consulta deletada!");
    }

}
