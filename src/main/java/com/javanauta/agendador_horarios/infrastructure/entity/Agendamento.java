package com.javanauta.agendador_horarios.infrastructure.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agendamento")
public class Agendamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String servico;	
	private String profissional;		
	private LocalDateTime dataHoraAgendamento;	
	private String cliente;
	private String telefoneCliente;
	
	// Initialize on persist so clients can't accidentally override or null it during deserialization
	@Column(name = "data_insercao", updatable = false)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime dataInsercao;

	@PrePersist
	public void prePersist() {
		if (this.dataInsercao == null) {
			this.dataInsercao = LocalDateTime.now();
		}
	}

}