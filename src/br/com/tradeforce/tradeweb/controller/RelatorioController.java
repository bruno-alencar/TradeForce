package br.com.tradeforce.tradeweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tradeforce.tradeweb.model.Tarefa;
import br.com.tradeforce.tradeweb.to.RelatorioTo;

@RestController
public class RelatorioController {
	
//	@Autowired
//	private RelatorioTo relatorioTo;
//	
//	@RequestMapping(value="/relatorio", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public List<Tarefa> gerarRelatorio(){
////		this.relatorioTo.gerarRelatorio();
//		return null;
//		
//	};
//	
//	public void gerarRelatorioPorPromotor(){
//		
//	}
}
