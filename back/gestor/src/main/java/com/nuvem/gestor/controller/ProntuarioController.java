package com.nuvem.gestor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nuvem.gestor.domain.DTO.ProntuarioDTO;
import com.nuvem.gestor.service.ProntuarioService;

@RestController
@CrossOrigin("*")
@RequestMapping("/prontuarios")
public class ProntuarioController {
    @Autowired
    private ProntuarioService prontuarioService;

    @GetMapping
    public List<ProntuarioDTO> listarProntuarios() {
        return prontuarioService.listarProntuarios();
    }
    
    @PostMapping
    public ResponseEntity<String> gerarProntuario(@RequestBody ProntuarioDTO prontuarioDTO){
        prontuarioService.gerarProntuario(prontuarioDTO);
        return ResponseEntity.ok("Prontuario criado!");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody ProntuarioDTO prontuarioDTO){
        prontuarioService.atualizarProntuario(id, prontuarioDTO);
        return ResponseEntity.ok("Dados do prontuario atualizados!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        prontuarioService.deletarProntuario(id);
        return ResponseEntity.ok("Prontuario deletado!");
    }
}
