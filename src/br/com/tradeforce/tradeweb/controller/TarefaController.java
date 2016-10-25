package br.com.tradeforce.tradeweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tradeforce.tradeweb.dao.TarefaDao;
import br.com.tradeforce.tradeweb.to.TarefaTo;

@RestController
public class TarefaController {

	@Autowired
	TarefaDao tarefaDao = new TarefaDao();
	
	@RequestMapping(value="", method = RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void init(){
		
	}
	
	@RequestMapping(value="gerarRota", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String gerarRota(@RequestBody String strlPromotor, @RequestBody String strlMercados){
		
		TarefaTo tarefaTo = new TarefaTo();
		tarefaTo.inserir(strlPromotor, strlMercados);
		
		return null;
	}
	
}
