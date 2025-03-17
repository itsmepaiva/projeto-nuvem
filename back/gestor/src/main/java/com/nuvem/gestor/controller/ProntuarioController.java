package com.nuvem.gestor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nuvem.gestor.domain.Consulta;
import com.nuvem.gestor.domain.Prontuario;
import com.nuvem.gestor.domain.DTO.ConsultaDTO;
import com.nuvem.gestor.domain.DTO.ProntuarioDTO;
import com.nuvem.gestor.service.ProntuarioService;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {
    @Autowired
    private ProntuarioService prontuarioService;

    @GetMapping
    public List<Prontuario> listarProntuarios() {
        return prontuarioService.listarProntuarios();
    }
    
    @PostMapping
    public Prontuario gerarProntuario(@RequestBody ProntuarioDTO prontuarioDTO){
        return prontuarioService.gerarProntuario(prontuarioDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> atualizar(@RequestParam Long id, @RequestBody ProntuarioDTO prontuarioDTO){
        prontuarioService.atualizarProntuario(id, prontuarioDTO);
        return ResponseEntity.ok("Dados do prontuario atualizados!");
    }

    @DeleteMapping
    public ResponseEntity<String> deletar(@RequestParam Long id){
        prontuarioService.deletarProntuario(id);
        return ResponseEntity.ok("Prontuario deletado!");
    }
}
