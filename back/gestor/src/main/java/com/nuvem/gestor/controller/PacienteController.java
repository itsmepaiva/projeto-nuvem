package com.nuvem.gestor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nuvem.gestor.domain.Paciente;
import com.nuvem.gestor.domain.DTO.PacienteDTO;
import com.nuvem.gestor.service.PacienteService;


@RestController
@CrossOrigin("*")
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> listarPacientes(){
        return pacienteService.listar();
    }

    @PostMapping
    public ResponseEntity<String> criarPaciente(@RequestBody PacienteDTO pacienteDTO){
        pacienteService.criar(pacienteDTO);
        return ResponseEntity.ok("Paciente criado!");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO){
        pacienteService.atualizar(id, pacienteDTO);
        return ResponseEntity.ok("Dados do(a) Paciente atualizados!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        pacienteService.deletar(id);
        return ResponseEntity.ok("Dados do(a) Paciente deletados!");
    }
}
