package com.nuvem.gestor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.nuvem.gestor.domain.Medico;
import com.nuvem.gestor.domain.DTO.MedicoDTO;
import com.nuvem.gestor.service.MedicoService;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public List<Medico> retornar() {
        return medicoService.listar();
    }
    
    @PostMapping
    public ResponseEntity<String> criar(@RequestBody MedicoDTO medicoDTO){
        medicoService.criar(medicoDTO);
        return ResponseEntity.ok("Medico(a) criado!");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO){
        medicoService.atualizar(id, medicoDTO);
        return ResponseEntity.ok("Dados do Medico(a) atualizados!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        medicoService.deletar(id);
        return ResponseEntity.ok("Dados do Medico(a) deletado!");
    }
}
