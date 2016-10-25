package br.com.tradeforce.tradeweb.to;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.tradeforce.tradeweb.dao.TarefaDao;
import br.com.tradeforce.tradeweb.model.Mercado;
import br.com.tradeforce.tradeweb.model.Promotor;
import br.com.tradeforce.tradeweb.model.Tarefa;

public class TarefaTo {

	@Autowired
	TarefaDao tarefaDao = new TarefaDao();

	public void inserir(String strlPromotor, String strlMercados){

		Promotor promotor = new Promotor();
		List<Mercado> listMercados = new ArrayList<Mercado>();


		JSONObject job2;
		JSONObject job;
		
		try {
			//Pegar promotor
			job2 = new JSONObject(strlPromotor);
			promotor.setId(Long.parseLong(job2.getString("id")));

			//Pegar mercados adicionados
			job = new JSONObject(strlMercados);
			JSONArray arrayMercados = job.getJSONArray("mercados");

			for(int i = 0; i < arrayMercados.length(); i++){
				Mercado mercado = new Mercado();
				mercado.setId(Long.parseLong(job2.getString("id")));

				listMercados.add(mercado);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		Tarefa tarefa = new Tarefa();
		tarefa.setPromotor(promotor);
		tarefa.setMercados(listMercados);		
		
		tarefaDao.inserir(tarefa);
		
	}
}
