package com.nuvem.gestor.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvem.gestor.domain.Medico;
import com.nuvem.gestor.domain.DTO.MedicoDTO;
import com.nuvem.gestor.repository.MedicoRepository;

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
        Optional<Medico> medico = medicoRepository.findById(id);
        Medico medico1 = medico.get();
        if(medicoDTO.getCRM() != null){
            medico1.setCrm(medicoDTO.getCRM());
        }
        if (medicoDTO.getEspecialidade() != null) {
            medico1.setEspecialidade(medicoDTO.getCRM());
        }
        if (medicoDTO.getNome() != null) {
            medico1.setNome(medicoDTO.getNome());
        }
        return medicoRepository.save(medico1);
    }

    public void deletar(Long id){
        medicoRepository.deleteById(id);
    }
}
