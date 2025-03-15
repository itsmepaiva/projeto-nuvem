package com.nuvem.gestor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvem.gestor.domain.Paciente;
import com.nuvem.gestor.domain.DTO.PacienteDTO;
import com.nuvem.gestor.repository.PacienteRepository;

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
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        Paciente paciente1 = paciente.get();
        if(pacienteDTO.getCpf() != null){
            paciente1.setCpf(pacienteDTO.getCpf());
        }
        if (pacienteDTO.getNome() != null) {
            paciente1.setNome(pacienteDTO.getNome());
        }
        if (pacienteDTO.getEndereco() != null) {
            paciente1.setEndereco(pacienteDTO.getEndereco());
        }
        if(pacienteDTO.getIdade() != null){
            paciente1.setIdade(pacienteDTO.getIdade());
        }
        return pacienteRepository.save(paciente1);
    }

    public void deletar(Long id){
        pacienteRepository.deleteById(id);
    }
}
