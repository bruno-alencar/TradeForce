package br.com.tradeforce.tradeweb.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tradeforce.tradeweb.model.Mercado;
import br.com.tradeforce.tradeweb.to.MercadoTo;

@RestController
public class MercadoController {
//	@Autowired
//	private MercadoDao mercadoDao;

	@Autowired
	private MercadoTo mercadoTo;

	@RequestMapping(value="/mercado", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Mercado> inserir(@RequestBody Mercado mercado){
		try {
			mercadoTo.inserir(mercado); //Insere a lista no banco de dados
			
			URI location = new URI("/mercado/"+mercado.getId()); //Cria o URI
			
			return ResponseEntity.created(location).body(mercado);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/mercado", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Mercado> listar(){
		return mercadoTo.listar();
	}
	
//	@RequestMapping(value="/mercado/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public Mercado consultarPorId(@PathVariable("id") Long id){
//		return mercadoTo.consultarPorId(id);
//	}
	
	
	@RequestMapping(value="/mercado/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable("id") long idMercado){
		mercadoTo.excluir(idMercado);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/mercado/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> alterar(@PathVariable("id") long idMercado, @RequestBody Mercado mercado) {
		try{
			mercado.setId(idMercado);
			mercadoTo.alterar(mercado);
			return ResponseEntity.noContent().build();
		} catch (Exception e){
			return ResponseEntity.notFound().build();
		  }
	}
	
}
	
