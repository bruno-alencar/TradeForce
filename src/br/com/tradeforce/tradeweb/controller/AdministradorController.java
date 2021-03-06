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

import br.com.tradeforce.tradeweb.model.Administrador;
import br.com.tradeforce.tradeweb.model.Administrador;
import br.com.tradeforce.tradeweb.to.AdministradorTo;

@RestController
public class AdministradorController {
	
	@Autowired
	AdministradorTo administradorTo;
	
	
	@RequestMapping(value="/administrador", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Administrador> inserir(@RequestBody Administrador administrador){
		try {
			
			administradorTo.inserir(administrador); //Insere a lista no banco de dados
			
			URI location = new URI("/administrador/"+administrador.getId()); //Cria o URI
			
			return ResponseEntity.created(location).body(administrador);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@RequestMapping(value="/administrador", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Administrador> listar(){
		return administradorTo.listar();
	}
	
	@RequestMapping(value="/administrador/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Administrador consultarPorId(@PathVariable("id") Long id){
		return administradorTo.consultarPorId(id);
	}
	
	@RequestMapping(value="/administrador/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable("id") long idAdministrador){
		administradorTo.excluir(idAdministrador);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/administrador/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> alterar(@PathVariable("id") long idAdministrador, @RequestBody Administrador administrador) {
		try{
			administrador.setId(idAdministrador);
			administradorTo.alterar(administrador);
			return ResponseEntity.noContent().build();
		} catch (Exception e){
			return ResponseEntity.notFound().build();
		  }
	}
}
