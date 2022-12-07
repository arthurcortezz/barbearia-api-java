package com.blackjack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blackjack.models.Agendamento;
import com.blackjack.repository.AgendamentoRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value="/agendamento")
public class AgendamentoController {
	
	@Autowired
	private AgendamentoRepository ag;
	
	@RequestMapping(value="/criar", method=RequestMethod.POST)
	public Agendamento criar(@RequestBody Agendamento agendamento){
		ag.save(agendamento);
		return agendamento;
	}	
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public Iterable<Agendamento> listar(){
		return ag.findAll();
	}
}
