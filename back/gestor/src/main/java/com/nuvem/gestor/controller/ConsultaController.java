package com.nuvem.gestor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nuvem.gestor.domain.DTO.ConsultaDTO;
import com.nuvem.gestor.service.ConsultaService;

@RestController
@CrossOrigin("*")
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public List<ConsultaDTO> retornar() {
        return consultaService.listarConsultas();
    }
    
    @PostMapping
    public ResponseEntity<String> gerarConsulta(@RequestBody ConsultaDTO consultaDTO){
        consultaService.marcarConsulta(consultaDTO);
        return ResponseEntity.ok("retorno efetuado");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody ConsultaDTO consultaDTO){
        consultaService.atualizarConsulta(id, consultaDTO);
        return ResponseEntity.ok("Dados da consulta atualizados!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        consultaService.excluirConsulta(id);
        return ResponseEntity.ok("Consulta deletada!");
    }

}
