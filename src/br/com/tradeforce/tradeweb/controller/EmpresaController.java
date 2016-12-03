
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

import br.com.tradeforce.tradeweb.model.Empresa;
import br.com.tradeforce.tradeweb.to.EmpresaTo;

@RestController
public class EmpresaController {
	
	@Autowired
	private EmpresaTo empresaTo;
	
	@RequestMapping(value="/empresa", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Empresa> inserir(@RequestBody Empresa empresa){
		try {
			empresaTo.inserir(empresa); //Insere a lista no banco de dados

			URI location = new URI("/empresa/"+empresa.getId()); //Cria o URI

			return ResponseEntity.created(location).body(empresa);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/empresa", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Empresa> listar(){
		return empresaTo.listar();
	}
	
	@RequestMapping(value="/empresa/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Empresa consultarPorId(@PathVariable("id") Long id){
		return empresaTo.consultarPorId(id);
	}
	
	@RequestMapping(value="/empresa/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable("id") long idEmpresa){
		empresaTo.excluir(idEmpresa);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/empresa/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> alterar(@PathVariable("id") long idEmpresa, @RequestBody Empresa empresa) {
		try{
			empresa.setId(idEmpresa);
			empresaTo.alterar(empresa);
			return ResponseEntity.noContent().build();
		} catch (Exception e){
			return ResponseEntity.notFound().build();
		  }
	}
}
