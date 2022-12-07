package com.blackjack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blackjack.models.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, String>{
	Agendamento findByCodigo(long codigo);
}
