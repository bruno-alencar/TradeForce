package br.com.tradeforce.tradeweb.controller;


import java.net.URI;
import java.util.List;

import org.json.JSONObject;
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
import br.com.tradeforce.tradeweb.model.Empresa;
import br.com.tradeforce.tradeweb.model.Localizacao;
import br.com.tradeforce.tradeweb.model.Promotor;

@RestController
public class PromotorController {

	@Autowired
	PromotorDao promotorDao = new PromotorDao();
	
	@RequestMapping(value="/promotor", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Promotor> inserir(@RequestBody String strlPromotor){
		try {
			JSONObject job = new JSONObject(strlPromotor);
			
			Promotor promotor = new Promotor();
			promotor.setLogin(job.getString("login"));
			promotor.setNome(job.getString("nome"));
			promotor.setSenha(job.getString("senha"));
			promotor.setIdade(job.getInt("idade"));
			
			JSONObject job2 = new JSONObject("localizacao");
			Localizacao localizacao = new Localizacao();
			localizacao.setLatitude(job2.getDouble("latitude"));
			localizacao.setLongitude(job2.getDouble("longitude"));
			
			promotor.setLocalizacao(localizacao);
			
			Empresa empresa = new Empresa();
			JSONObject job3 = new JSONObject("Empresa");
			empresa.setId(job3.getLong("idEmpresa"));
			
			promotor.setEmpresa(empresa);
			
			
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
	
	@RequestMapping(value="/promotor/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable("id") long idPromotor){
		promotorDao.excluir(idPromotor);
		return ResponseEntity.noContent().build();
	}
}

