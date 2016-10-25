package br.com.tradeforce.tradeweb.to;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.tradeforce.tradeweb.dao.TarefaDao;
import br.com.tradeforce.tradeweb.model.Localizacao;
import br.com.tradeforce.tradeweb.model.Mercado;
import br.senai.sp.informatica.todolist.modelo.ItemLista;
import br.senai.sp.informatica.todolist.modelo.Lista;

public class TarefaTo {
	
	@Autowired
	TarefaDao tarefaDao = new TarefaDao();
	
	public void inserir(String strlPromotor, String strlMercado){
		try {
			JSONObject job = new JSONObject(strlMercado);
			List<Mercado> listMercado = new ArrayList<Mercado>();
			
			
			Mercado mercado = new Mercado();
			mercado.setNome(job.getString("nome"));
			mercado.setRazaoSocial(job.getString("razaoSocial"));
			mercado.setEndereco(job.getString("endereco"));
			
			Localizacao localizacao = new Localizacao();
			localizacao.setLatitude(Double.parseDouble("latitude"));
			localizacao.setLongitude(Double.parseDouble("longitude"));
			
			
			lista.setTitulo(job.getString("titulo"));
			List<ItemLista> itens = new ArrayList<>();
			
			JSONArray arrayItens = job.getJSONArray("itens");
			
			for(int i = 0; i < arrayItens.length(); i++){
				ItemLista item = new ItemLista();
				item.setDescricao(arrayItens.getString(i));
				item.setLista(lista);
				itens.add(item);
			}
			
			lista.setItens(itens); // adicionar os itens a lista
			
			listaDao.inserir(lista); //Insere a lista no banco de dados
			
			URI location = new URI("/lista/"+lista.getId()); //Cria o URI
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
