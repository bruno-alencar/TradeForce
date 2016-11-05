package br.com.tradeforce.tradeweb.controller;
import java.net.URI;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tradeforce.tradeweb.dao.AdministradorDao;
import br.com.tradeforce.tradeweb.model.Administrador;

@RestController
public class AdministradorController {
	
	@Autowired
	AdministradorDao administradorDao = new AdministradorDao();
	
	
	@RequestMapping(value="/administrador", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Administrador> inserir(@RequestBody String strlAdministrador){
		try {
			JSONObject job = new JSONObject(strlAdministrador);
			
			Administrador administrador = new Administrador();
			administrador.setLogin(job.getString("login"));
			administrador.setNome(job.getString("nome"));
			administrador.setSenha(job.getString("senha"));
			
			administradorDao.inserir(administrador); //Insere a lista no banco de dados
			
			URI location = new URI("/lista/"+administrador.getId()); //Cria o URI
			
			return ResponseEntity.created(location).body(administrador);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
