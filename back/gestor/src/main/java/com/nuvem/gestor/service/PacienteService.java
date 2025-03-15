package com.nuvem.gestor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvem.gestor.domain.Paciente;
import com.nuvem.gestor.domain.DTO.PacienteDTO;
import com.nuvem.gestor.repository.PacienteRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente criar(PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        paciente.setNome(pacienteDTO.getNome());
        paciente.setCpf(pacienteDTO.getCpf());
        paciente.setEndereco(pacienteDTO.getEndereco());
        paciente.setIdade(pacienteDTO.getIdade());
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> listar(){
        return pacienteRepository.findAll();
    }

    public Paciente atualizar(Long id, PacienteDTO pacienteDTO){
        Paciente paciente = pacienteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Paciente com id: " + id + " nao foi encontrado"));
        if(pacienteDTO.getCpf() != null && !pacienteDTO.getCpf().trim().isEmpty()){
            paciente.setCpf(pacienteDTO.getCpf());
        }
        if (pacienteDTO.getNome() != null && !pacienteDTO.getNome().trim().isEmpty()) {
            paciente.setNome(pacienteDTO.getNome());
        }
        if (pacienteDTO.getEndereco() != null && !pacienteDTO.getEndereco().trim().isEmpty()) {
            paciente.setEndereco(pacienteDTO.getEndereco());
        }
        if(pacienteDTO.getIdade() != null && !pacienteDTO.getIdade().toString().trim().isEmpty()){
            paciente.setIdade(pacienteDTO.getIdade());
        }
        return pacienteRepository.save(paciente);
    }

    public void deletar(Long id){
        pacienteRepository.deleteById(id);
    }
}
