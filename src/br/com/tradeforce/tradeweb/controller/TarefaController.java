package br.com.tradeforce.tradeweb.controller;

import java.net.URI;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tradeforce.tradeweb.model.Auxiliar;
import br.com.tradeforce.tradeweb.model.Tarefa;
import br.com.tradeforce.tradeweb.to.TarefaTo;

@RestController
public class TarefaController {

	
	@Autowired
	TarefaTo tarefaTo;

	@RequestMapping(value = "/tarefa", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Tarefa> listar() {
		return tarefaTo.listar();
	}

	@RequestMapping(value = "/tarefa", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Tarefa> gerarRota(@RequestBody Auxiliar auxiliar) {
		try {
			Tarefa tarefa = new Tarefa();
			tarefaTo.inserir(auxiliar);
			
			URI location = new URI("/tarefa/" + tarefa.getId()); // Cria o URI

			return ResponseEntity.created(location).body(tarefa);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/tarefa/mostrar/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Tarefa mostrarTarefa(@PathVariable("id") Long id) {
		return tarefaTo.consultarPorPromotorId(id);
	}

	@RequestMapping(value = "/tarefa/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable("id") long idTarefa) {
		tarefaTo.excluir(idTarefa);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/tarefa/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> alterar(@PathVariable("id") long idTarefa, @RequestBody Tarefa tarefa) {
		try{
			tarefa.setId(idTarefa);
			tarefaTo.alterar(tarefa);
			return ResponseEntity.noContent().build();
		} catch (Exception e){
			return ResponseEntity.notFound().build();
		  }
	}
	
//	@RequestMapping(value="/tarefa/{idTarefa}", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public ResponseEntity<Void> marcarFeito(@PathVariable  Long idItem, @RequestBody String strFeito){
//		try {
//			JSONObject job = new JSONObject(strFeito);
//			itemDao.marcarFeito(idItem, job.getBoolean("feito"));
//			HttpHeaders responseHeader = new HttpHeaders();
//			URI location = new URI("/item/"+idItem);
//			responseHeader.setLocation(location);
//			return new ResponseEntity<>(responseHeader,HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
}
