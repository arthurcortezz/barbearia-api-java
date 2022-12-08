package com.blackjack.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.blackjack.models.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, String>{
	Agendamento findByCodigo(long codigo);
	
	@Modifying
	@Transactional
	@Query("update Agendamento a set a.data = :data, a.horario = :horario, a.local = :local where a.codigo = :codigo")
	void updateAgendamento(String data, String horario, String local, long codigo);
}
