package com.javanauta.agendador_horarios.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.javanauta.agendador_horarios.infrastructure.entity.Agendamento;
import com.javanauta.agendador_horarios.infrastructure.repository.AgendamentoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

	private final AgendamentoRepository agendamentoRepository;
	
	public Agendamento salvarAgendamento(Agendamento agendamento) {
		
		LocalDateTime horaAgendamento = agendamento.getDataHoraAgendamento();
		LocalDateTime horaFim = agendamento.getDataHoraAgendamento().plusHours(1);
		
		Agendamento agendados = agendamentoRepository.findByServicoAndDataHoraAgendamentoBetween(
				agendamento.getServico(), 
				horaAgendamento, 
				horaFim
			);
		
		if(Objects.nonNull(agendados)) {
			throw new RuntimeException("Já existe um agendamento para esse serviço nesse horário");
		}
		
		return agendamentoRepository.save(agendamento);
	}
	
	public void deletarAgendamento(String cliente, LocalDateTime dataHoraAgendamento) {
		agendamentoRepository.deleteByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);
	}
	
	public Agendamento buscarAgendamentoDia(LocalDate data) {
		LocalDateTime inicio = data.atStartOfDay();
		LocalDateTime fim = data.atTime(23, 59, 59);
		
		return agendamentoRepository.findByDataHoraAgendamentoBetween(inicio, fim);
	}
	
	public Agendamento alterarAgendamento(Agendamento agendamento, String cliente, LocalDateTime dataHoraAgendamento) {
		Agendamento agenda = agendamentoRepository.findByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);
		
		if(Objects.isNull(agenda)) {
			throw new RuntimeException("Horario não está agendado");
		}
		
		agendamento.setId(agenda.getId());
		return agendamentoRepository.save(agendamento);
	}
	
}
