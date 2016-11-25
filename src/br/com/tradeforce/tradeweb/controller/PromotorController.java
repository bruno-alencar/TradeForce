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

import br.com.tradeforce.tradeweb.dao.PromotorDao;
import br.com.tradeforce.tradeweb.model.Promotor;
import br.com.tradeforce.tradeweb.to.PromotorTo;

@RestController
public class PromotorController {

	@Autowired
	private PromotorDao promotorDao = new PromotorDao();
	@Autowired
	private PromotorTo promotorTo;
	
	@RequestMapping(value="/promotor", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Promotor> inserir(@RequestBody Promotor promotor){
		try {
			
			promotorDao.inserir(promotor); //Insere a lista no banco de dados
			
			URI location = new URI("/promotor/"+promotor.getId()); //Cria o URI
			
			return ResponseEntity.created(location).body(promotor);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/promotor", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Promotor> listar(){
		return promotorDao.listar();
	}
	
	@RequestMapping(value="/promotor/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Promotor consultarPorId(@PathVariable("id") Long id){
		return promotorTo.consultarPorId(id);
	}
	
	
	@RequestMapping(value="/promotor/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable("id") long idPromotor){
		promotorDao.excluir(idPromotor);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/promotor/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void>  atualizar(@PathVariable("id") long idPromotor, @RequestBody Promotor promotor) {
		try{
			promotor.setId(idPromotor);
			promotorDao.atualizar(promotor);
			return ResponseEntity.noContent().build();
		} catch (Exception e){
			return ResponseEntity.notFound().build();
		  }
	}
}

