package com.nuvem.gestor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nuvem.gestor.domain.Exame;
import com.nuvem.gestor.domain.DTO.ExameDTO;
import com.nuvem.gestor.domain.DTO.ExameGetDTO;
import com.nuvem.gestor.service.ExameService;

@RestController
@CrossOrigin("*")
@RequestMapping("/exames")
public class ExameController {
    @Autowired
    private ExameService exameService;

   @GetMapping
    public List<ExameGetDTO> retornar() {
        return exameService.listarExames();
    }

    @GetMapping("/todos")
    public List<Exame> retornarTodos(){
        return exameService.listarTodosExames();
    }
    
    @PostMapping
    public ResponseEntity<String> marcarExame(@RequestBody ExameDTO exameDTO){
        exameService.marcarExame(exameDTO);
        return ResponseEntity.ok("exame criado!");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody ExameDTO exameDTO){
        exameService.atualizarExame(id, exameDTO);
        return ResponseEntity.ok("Dados do exame atualizados!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        exameService.excluirExame(id);
        return ResponseEntity.ok("Exame deletado!");
    }
 

}
