package com.javanauta.agendador_horarios.infrastructure.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javanauta.agendador_horarios.infrastructure.entity.Agendamento;

import jakarta.transaction.Transactional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

	Agendamento findByServicoAndDataHoraAgendamentoBetween(String servico, LocalDateTime inicio, LocalDateTime fim);
	
	@Transactional
	void deleteByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento, String cliente);
	
	Agendamento findByDataHoraAgendamentoBetween(LocalDateTime inicio, LocalDateTime fim);

	Agendamento findByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento, String cliente);

}
  