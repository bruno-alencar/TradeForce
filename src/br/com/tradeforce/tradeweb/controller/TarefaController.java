package br.com.tradeforce.tradeweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	
	@RequestMapping(value="gerarRota", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String gerarRota(@RequestBody String strlPromotor, @RequestBody String strlMercados){
		
		
		tarefaTo.inserir(strlPromotor, strlMercados);
		
		return null;
	}
	
	@RequestMapping(value="/tarefa/mostrar/{id}", method = RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Tarefa mostrarTarefa(@PathVariable("id") Long id){
		return tarefaTo.consultarPorPromotorId(id);
	}

}
