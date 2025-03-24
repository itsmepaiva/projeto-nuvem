package com.nuvem.gestor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvem.gestor.domain.Consulta;
import com.nuvem.gestor.domain.Prontuario;
import com.nuvem.gestor.domain.DTO.ProntuarioDTO;
import com.nuvem.gestor.repository.ConsultaRepository;
import com.nuvem.gestor.repository.ProntuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProntuarioService {
    @Autowired
    private ProntuarioRepository prontuarioRepository;
    @Autowired
    private ConsultaRepository consultaRepository;

    public Prontuario gerarProntuario(ProntuarioDTO prontuarioDTO){
        Prontuario prontuario = new Prontuario();
        Consulta consulta = consultaRepository.findById(prontuarioDTO.getConsultaId())
            .orElseThrow(() -> new EntityNotFoundException("Consulta com id: " + prontuarioDTO.getConsultaId() + " nao foi encontrada"));
        prontuario.setDiagnostico(prontuarioDTO.getDiagnostico());
        prontuario.setSintomas(prontuarioDTO.getSintomas());
        prontuario.setConsulta(consulta);
        prontuario.setPrecisaExame(prontuarioDTO.isPrecisaExame());
        return prontuarioRepository.save(prontuario);
    } 

    public List<Prontuario> listarProntuarios(){
        return prontuarioRepository.findAll();
    }

    public Prontuario atualizarProntuario(Long id, ProntuarioDTO prontuarioDTO){
        Prontuario prontuario = prontuarioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Prontuario com id: " + id + " nao foi encontrado"));

        if(prontuarioDTO.getDiagnostico() != null && !prontuarioDTO.getDiagnostico().toString().trim().isEmpty()){
            prontuario.setDiagnostico(prontuarioDTO.getDiagnostico());
        }

        prontuario.setPrecisaExame(prontuarioDTO.isPrecisaExame());

        if (prontuarioDTO.getSintomas() != null && !prontuarioDTO.getSintomas().isEmpty()){
            prontuario.setSintomas(prontuarioDTO.getSintomas());
        }
        if (prontuarioDTO.getConsultaId() != null && !prontuarioDTO.getConsultaId().toString().trim().isEmpty()){
            Consulta consulta = consultaRepository.findById(prontuarioDTO.getConsultaId())
                .orElseThrow(() -> new EntityNotFoundException("Consulta com id: " + prontuarioDTO.getConsultaId() + " nao foi encontrada"));
            prontuario.setConsulta(consulta);
        }
        
        return prontuarioRepository.save(prontuario);
    }

    public void deletarProntuario(Long id){
        prontuarioRepository.deleteById(id);
    }

}


