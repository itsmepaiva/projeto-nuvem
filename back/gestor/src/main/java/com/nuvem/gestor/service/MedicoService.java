package com.nuvem.gestor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvem.gestor.domain.Medico;
import com.nuvem.gestor.domain.DTO.MedicoDTO;
import com.nuvem.gestor.repository.MedicoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico criar(MedicoDTO medicoDTO) {
        Medico medico = new Medico();
        medico.setNome(medicoDTO.getNome());
        medico.setCrm(medicoDTO.getCRM());
//DEFINIR UM ENUM PARA ESPECIALIDADES 
        medico.setEspecialidade(medicoDTO.getEspecialidade());
        return medicoRepository.save(medico);
    }

    public List<Medico> listar(){
        return medicoRepository.findAll();
    }

    public Medico atualizar(Long id, MedicoDTO medicoDTO){
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medico com id: " + id + " nao foi encontrado"));
        if(medicoDTO.getCRM() != null && !medicoDTO.getCRM().trim().isEmpty()){
            medico.setCrm(medicoDTO.getCRM());
        }
        if (medicoDTO.getEspecialidade() != null && !medicoDTO.getEspecialidade().trim().isEmpty()) {
            medico.setEspecialidade(medicoDTO.getEspecialidade());
        }
        if (medicoDTO.getNome() != null && !medicoDTO.getNome().trim().isEmpty()) {
            medico.setNome(medicoDTO.getNome());
        }
        return medicoRepository.save(medico);
    }

    public void deletar(Long id){
        medicoRepository.deleteById(id);
    }
}
