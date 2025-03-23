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
    public Medico criar(@RequestBody MedicoDTO medicoDTO){
        return medicoService.criar(medicoDTO);
    }

    @PatchMapping
    public ResponseEntity<String> atualizar(@RequestParam Long id, @RequestBody MedicoDTO medicoDTO){
        medicoService.atualizar(id, medicoDTO);
        return ResponseEntity.ok("Dados do Medico(a) atualizados!");
    }

    @DeleteMapping
    public void deletar(@RequestParam Long Id){
        medicoService.deletar(Id);
    }
}
