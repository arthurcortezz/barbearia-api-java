package com.blackjack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blackjack.models.Conta;
import com.blackjack.repository.ContaRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value="/conta")
public class ContaController {
	
	@Autowired
	private ContaRepository ct;
	
	@RequestMapping(value="/cadastro", method=RequestMethod.POST)
	public Conta criar(@RequestBody Conta conta){
		ct.save(conta);
		return conta;
	}	
	
	@GetMapping(value="/login")
	public ResponseEntity<Conta> FazerLogin(
			@RequestParam(defaultValue="") String email,
			@RequestParam(defaultValue="") String senha){
		Long result = ct.fazerLogin(email, senha);
		if(result != null) {
			Conta response = ct.findByCodigo(result);
			return ResponseEntity.ok(response);
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@GetMapping(value="/verificaEmail")
	public ResponseEntity<Page<Conta>> BuscarPeloEmail(
			@RequestParam(defaultValue="") String email,
			Pageable pageable){
		Page<Conta> result = ct.procurarConta(email, pageable);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value="/buscarUsuario")
	public ResponseEntity<Conta> BuscarUsuario(
			@RequestParam(defaultValue="") Long id,
			Pageable pageable){
		Conta result = ct.findByCodigo(id);
		return ResponseEntity.ok(result);
	}
}
