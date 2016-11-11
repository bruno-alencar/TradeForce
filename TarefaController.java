package br.com.tradeforce.tradeweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tradeforce.tradeweb.dao.TarefaDao;
import br.com.tradeforce.tradeweb.model.Tarefa;
import br.com.tradeforce.tradeweb.to.TarefaTo;

@RestController
public class TarefaController {

	@Autowired
	TarefaDao tarefaDao = new TarefaDao();
	TarefaTo tarefaTo = new TarefaTo();
	
	@RequestMapping(value="", method = RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void init(){
		
	}
	
	@RequestMapping(value="tarefa", method = RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Tarefa> listar(){
		return tarefaTo.listar();
	}
	
	@RequestMapping(value="/tarefa", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String gerarRota(@RequestBody String strlPromotor, @RequestBody String strlMercados){
		tarefaTo.inserir(strlPromotor, strlMercados);
		return null;
	}
	
	@RequestMapping(value="/tarefa/mostrar/{id}", method = RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Tarefa mostrarTarefa(@PathVariable("id") Long id){
		return tarefaTo.consultarPorPromotorId(id);
	}
	
	@RequestMapping(value="/tarefa/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable("id") long idTarefa){
		tarefaTo.excluir(idTarefa);
		return ResponseEntity.noContent().build();
	}
}
