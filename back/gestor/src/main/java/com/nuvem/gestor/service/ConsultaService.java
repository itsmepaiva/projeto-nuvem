package com.nuvem.gestor.service;

import java.util.ArrayList;
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
        consulta.setEPresencial(consultaDTO.isPresencial());
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        return consultaRepository.save(consulta);
    }

    //RETORNA TODAS CONSULTAS POREM CRIA UM DTO APENAS COM OS DADOS ESPECIFICADOS
    public List<ConsultaDTO> listarConsultas(){
        List<Consulta> consultas = consultaRepository.findAll();
        List<ConsultaDTO> consultaDTOs = new ArrayList<>();
        for(Consulta consulta : consultas){
            ConsultaDTO consultaDTO = new ConsultaDTO();
            consultaDTO.setNomePaciente(consulta.getPaciente().getNome());
            consultaDTO.setNomeMedico(consulta.getMedico().getNome());
            consultaDTO.setData(consulta.getData());
            consultaDTO.setHorario(consulta.getHorario());
            consultaDTO.setPresencial(consulta.isEPresencial());
            consultaDTO.setId(consulta.getId());
            consultaDTOs.add(consultaDTO);
        }
        return consultaDTOs;
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

        consulta.setEPresencial(consultaDTO.isPresencial());

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
