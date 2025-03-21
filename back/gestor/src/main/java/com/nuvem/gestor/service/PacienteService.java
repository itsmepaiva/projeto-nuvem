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
        paciente.setAltura(pacienteDTO.getAltura());
        paciente.setPeso(pacienteDTO.getPeso());
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
        if (pacienteDTO.getPeso() != null && !pacienteDTO.getPeso().toString().trim().isEmpty()) {
            paciente.setPeso(pacienteDTO.getPeso());
        }
        if (pacienteDTO.getAltura() != null && !pacienteDTO.getAltura().toString().trim().isEmpty()) {
            paciente.setAltura(pacienteDTO.getAltura());
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
