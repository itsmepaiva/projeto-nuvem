package com.nuvem.gestor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvem.gestor.domain.*;
import com.nuvem.gestor.domain.DTO.ConsultaDTO;
import com.nuvem.gestor.repository.*;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;



    public Consulta marcarConsulta(ConsultaDTO consultaDTO){
        Consulta consulta = new Consulta();
        Paciente paciente = pacienteRepository.findByNome(consultaDTO.getNomePaciente());
        Medico medico = medicoRepository.findByNome(consultaDTO.getNomeMedico());

        consulta.setData(consultaDTO.getData());
        consulta.setHorario(consultaDTO.getHorario());
        consulta.setEPresencial(consultaDTO.isEPresencial());
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarConsultas(){
        return consultaRepository.findAll();
    }

    public Consulta atualizarConsulta(Long id, ConsultaDTO consultaDTO){
        Consulta consulta = consultaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Consulta com id: " + id + " nao foi encontrada"));
            
        
        if(consultaDTO.getHorario() != null && !consultaDTO.getHorario().toString().trim().isEmpty()){
            consulta.setHorario(consultaDTO.getHorario());
        }

        if(consultaDTO.getData() != null && !consultaDTO.getData().toString().trim().isEmpty()){
            consulta.setData(consultaDTO.getData());
        }

        consulta.setEPresencial(consultaDTO.isEPresencial());

        if (consultaDTO.getNomeMedico() != null && !consultaDTO.getNomeMedico().trim().isEmpty()) {
            Medico medico = medicoRepository.findByNome(consultaDTO.getNomeMedico());
            consulta.setMedico(medico);
        }
        if (consultaDTO.getNomePaciente() != null && !consultaDTO.getNomePaciente().trim().isEmpty() ) {
            Paciente paciente = pacienteRepository.findByNome(consultaDTO.getNomePaciente());
            consulta.setPaciente(paciente);
        }

        return consultaRepository.save(consulta);
    }

    public void excluirConsulta(Long id){
        consultaRepository.deleteById(id);
    }
}
