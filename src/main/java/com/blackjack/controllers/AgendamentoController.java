package com.blackjack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blackjack.models.Agendamento;
import com.blackjack.repository.AgendamentoRepository;

@CrossOrigin("http://localhost:3000")
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
	
	@RequestMapping(value="/alterar", method=RequestMethod.POST)
	public ResponseEntity<String> alterar(@RequestBody Agendamento agendamento){
		ag.updateAgendamento(agendamento.getData(), agendamento.getHorario(), agendamento.getLocal(), agendamento.getCodigo());
		return new ResponseEntity<String>("Agendamento alterado com sucesso!", HttpStatus.OK);
	}
	
	@RequestMapping(value="/listarTodos", method=RequestMethod.GET)
	public Iterable<Agendamento> listarTodos(){
		return ag.findAll();
	}
	
	@RequestMapping(value="/deletar", method=RequestMethod.DELETE)
	public ResponseEntity<String> deletar(@RequestParam long codigo){
		Agendamento agendamento = ag.findByCodigo(codigo);
		ag.delete(agendamento);
		return new ResponseEntity<String>("Agendamento excluído com sucesso!", HttpStatus.OK);
	}
}
