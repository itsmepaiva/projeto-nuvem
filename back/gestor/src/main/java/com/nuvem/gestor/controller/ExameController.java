package com.nuvem.gestor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nuvem.gestor.domain.Exame;
import com.nuvem.gestor.domain.DTO.ExameDTO;
import com.nuvem.gestor.service.ExameService;

@RestController
@CrossOrigin("*")
@RequestMapping("/exames")
public class ExameController {
    @Autowired
    private ExameService exameService;

   @GetMapping
    public List<Exame> retornar() {
        return exameService.listarExames();
    }
    
    @PostMapping
    public Exame marcarExame(@RequestBody ExameDTO exameDTO){
        return exameService.marcarExame(exameDTO);
    }

    @PatchMapping
    public ResponseEntity<String> atualizar(@RequestParam Long id, @RequestBody ExameDTO exameDTO){
        exameService.atualizarExame(id, exameDTO);
        return ResponseEntity.ok("Dados do exame atualizados!");
    }

    @DeleteMapping
    public ResponseEntity<String> deletar(@RequestParam Long id){
        exameService.excluirExame(id);
        return ResponseEntity.ok("Exame deletado!");
    }
 

}
