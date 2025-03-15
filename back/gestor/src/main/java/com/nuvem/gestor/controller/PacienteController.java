package com.nuvem.gestor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuvem.gestor.domain.Paciente;
import com.nuvem.gestor.GestorApplication;
import com.nuvem.gestor.domain.DTO.PacienteDTO;
import com.nuvem.gestor.service.PacienteService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> listarPacientes(){
        return pacienteService.listar();
    }

    @PostMapping
    public Paciente criarPaciente(@RequestBody PacienteDTO pacienteDTO){
        return pacienteService.criar(pacienteDTO);
    }

    @PatchMapping
    public ResponseEntity<String> atualizar(Long id, PacienteDTO pacienteDTO){
        pacienteService.atualizar(id, pacienteDTO);
        return ResponseEntity.ok("Dados do(a) Paciente atualizados!");
    }

    @DeleteMapping
    public ResponseEntity<String> deletar(@RequestBody Long id){
        pacienteService.deletar(id);
        return ResponseEntity.ok("Dados do(a) Paciente deletados!");
    }
}
