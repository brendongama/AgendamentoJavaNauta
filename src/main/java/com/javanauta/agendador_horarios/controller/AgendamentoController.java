package com.javanauta.agendador_horarios.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javanauta.agendador_horarios.infrastructure.entity.Agendamento;
import com.javanauta.agendador_horarios.service.AgendamentoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agendamentos")
public class AgendamentoController {

	private final AgendamentoService agendamentoService;
	
	@PostMapping()
	public ResponseEntity<Agendamento> salvarAgendamento(@RequestBody Agendamento agendamento) {
		Agendamento agendamentoSalvo = agendamentoService.salvarAgendamento(agendamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoSalvo);
	}
	
	@DeleteMapping()
	public ResponseEntity<Void> deletarAgendamento(@RequestParam String cliente, @RequestParam String dataHoraAgendamento) {
		agendamentoService.deletarAgendamento(cliente, LocalDateTime.parse(dataHoraAgendamento));
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping()
	public ResponseEntity<Agendamento> buscarAgendamentoDia(@RequestParam LocalDate data) {
		Agendamento agendamento = agendamentoService.buscarAgendamentoDia(data);
		return ResponseEntity.ok(agendamento);
	}
	
	@PutMapping()
	public ResponseEntity<Agendamento> alterarAgendamento(
			@RequestBody Agendamento agendamento, 
			@RequestParam String cliente, 
			@RequestParam String dataHoraAgendamento) {
		
		Agendamento agendamentoAlterado = agendamentoService.alterarAgendamento(
				agendamento, 
				cliente, 
				LocalDateTime.parse(dataHoraAgendamento)
			);
		
		return ResponseEntity.ok(agendamentoAlterado);
	}

}
