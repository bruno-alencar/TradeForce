package br.com.tradeforce.tradeweb.controller;

import java.net.URI;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tradeforce.tradeweb.dao.MercadoDao;
import br.com.tradeforce.tradeweb.model.Localizacao;
import br.com.tradeforce.tradeweb.model.Mercado;

@RestController
public class MercadoController {
	@Autowired
	private MercadoDao mercadoDao;
	
	@RequestMapping(value="/mercado", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Mercado> inserir(@RequestBody String strlMercado){
		try {
			JSONObject job = new JSONObject(strlMercado);
			Mercado mercado = new Mercado();
			mercado.setNome(job.getString("nome"));
			mercado.setRazaoSocial(job.getString("razaoSocial"));
			mercado.setEndereco(job.getString("endereco"));
			
			Localizacao localizacao = new Localizacao();
			localizacao.setLatitude(Double.parseDouble("latitude"));
			localizacao.setLongitude(Double.parseDouble("longitude"));
			
			mercado.setLocalizacao(localizacao);
			
			mercadoDao.inserir(mercado); //Insere a lista no banco de dados
			
			URI location = new URI("/lista/"+mercado.getId()); //Cria o URI
			
			return ResponseEntity.created(location).body(mercado);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/listarMercados", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Mercado> listar(){
		return mercadoDao.listar();
	}

}
