package com.blackjack.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blackjack.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{
	Conta findByCodigo(long codigo);

	@Query("SELECT email FROM Conta ct WHERE LOWER(ct.email) = LOWER(:email)") 
	Page<Conta> procurarConta(String email, Pageable pageable);
	
	@Query("SELECT codigo FROM Conta ct WHERE LOWER(ct.email) = LOWER(:email) AND ct.senha = :senha") 
	Page<Conta> fazerLogin(String email, String senha, Pageable pageable);
	
	@Query("SELECT nome, email, celular FROM Conta ct WHERE id = :id") 
	Page<Conta> buscarUsuario(Long id, Pageable pageable);
	
	
}
