package com.nuvem.gestor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvem.gestor.domain.Exame;
import com.nuvem.gestor.domain.Prontuario;
import com.nuvem.gestor.domain.DTO.ExameDTO;
import com.nuvem.gestor.domain.DTO.ExameGetDTO;
import com.nuvem.gestor.repository.ExameRepository;
import com.nuvem.gestor.repository.ProntuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ExameService {
    @Autowired
    private ExameRepository exameRepository;
    @Autowired
    private ProntuarioRepository prontuarioRepository;

    public Exame marcarExame(ExameDTO exameDTO){
        Exame exame = new Exame();
        Prontuario prontuario = prontuarioRepository.findById(exameDTO.getProntuarioId())
            .orElseThrow(() -> new EntityNotFoundException("Prontuario nao foi encontrado"));
        exame.setProntuario(prontuario);
        exame.setData(exameDTO.getData()); 
        exame.setHora(exameDTO.getHora());
        exame.setNomePaciente(prontuario.getConsulta().getPaciente().getNome());
        exame.setTipoExame(exameDTO.getTipoExame());
        return exameRepository.save(exame);
    }

    public List<ExameGetDTO> listarExames(){
        List<Exame> exames = exameRepository.findAll();
        List<ExameGetDTO> exameGetDTOs = new ArrayList<>();
        for(Exame exame : exames){
            ExameGetDTO exameGetDTO = new ExameGetDTO();
            exameGetDTO.setData(exame.getData());
            exameGetDTO.setHora(exame.getHora());
            exameGetDTO.setId(exame.getId());
            exameGetDTO.setNomePaciente(exame.getNomePaciente());
            exameGetDTO.setTipoExame(exame.getTipoExame());
            exameGetDTOs.add(exameGetDTO);
        }
        return exameGetDTOs;
    }

    public List<Exame> listarTodosExames(){
        return exameRepository.findAll();
    }



    public Exame atualizarExame(Long id, ExameDTO exameDTO){
        Exame exame = exameRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Exame nao foi encontrado"));

        if(exameDTO.getProntuarioId() != null && !exameDTO.getProntuarioId().toString().trim().isEmpty()){
            Prontuario prontuario = prontuarioRepository.findById(exameDTO.getProntuarioId())
            .orElseThrow(() -> new EntityNotFoundException("Prontuario nao foi encontrado"));
            exame.setProntuario(prontuario);
            exame.setNomePaciente(prontuario.getConsulta().getPaciente().getNome());
        } 
        if(exameDTO.getTipoExame() != null && !exameDTO.getTipoExame().toString().trim().isEmpty()){
            exame.setTipoExame(exameDTO.getTipoExame());
        }

        if(exameDTO.getData() != null && !exameDTO.getData().toString().trim().isEmpty()){
            exame.setData(exameDTO.getData()); 
        }

        if(exameDTO.getHora() != null && !exameDTO.getHora().toString().trim().isEmpty()){
            exame.setHora(exameDTO.getHora());
        }
        return exameRepository.save(exame);
    }

    public void excluirExame(Long id){
        exameRepository.deleteById(id);
    }
}
