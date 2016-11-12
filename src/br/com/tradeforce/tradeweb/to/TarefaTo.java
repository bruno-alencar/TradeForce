package br.com.tradeforce.tradeweb.to;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.tradeforce.tradeweb.dao.TarefaDao;
import br.com.tradeforce.tradeweb.model.Mercado;
import br.com.tradeforce.tradeweb.model.Promotor;
import br.com.tradeforce.tradeweb.model.Rota;
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
	
	public List<Tarefa> listar(){
		gerarRotas();
		return tarefaDao.listar();
	}
	
	public Tarefa consultarPorPromotorId(Long idPromotor){
		Tarefa tarefa = tarefaDao.consultarPorPromotorId(idPromotor);
		return tarefa;
	}
	
	public void excluir(@PathVariable("id") long idTarefa){
		tarefaDao.excluir(idTarefa);
	}
	
	
	
	public void gerarRotas(){
		
//		JSONObject jobRoutes = new JSONObject("routes");
//		
//		JSONArray arrayRoutes = jobRoutes.getJSONArray("");
		String routes = this.conectar("https://maps.googleapis.com/maps/api/directions/json?origin=Rio+Pequeno,SP&destination=Butanta,SP&mode=transit&types=route&key=AIzaSyCQetlePSuE-z8nGmpKh3NNLkzP_hHJiwk");
		System.out.println(routes);
		try {
			JSONObject jobRoutes = new JSONObject(routes);
			
			JSONArray arrayRoutes = jobRoutes.getJSONArray("routes");
			List<Rota> rotas = new ArrayList<Rota>();
			
			for(int i = 0; i < arrayRoutes.length(); i ++){
				Rota rota = new Rota();
				JSONObject jobFare = arrayRoutes.getJSONObject(i).getJSONObject("fare");
				rota.setPreco(jobFare.getDouble("value"));
				
				JSONArray arrayLegs = arrayRoutes.getJSONObject(i).getJSONArray("legs");
				
				for(int j = 0; j < arrayLegs.length(); j++){
					
				}
				
				System.out.println(rota.getPreco());
				
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
	}
	
	private String conectar(String link){
		String jsonReturn = "";
		try {
			URL url = new URL(link);
			
			URLConnection urlConnection = url.openConnection();
			
			urlConnection.addRequestProperty("User-Agent", 
					"Mozilla/48.0 (compatible; MSIE 6.0; Windows NT 5.0)");
			
			InputStreamReader inputStreamReader= new InputStreamReader(urlConnection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String inputLine;
			
			while((inputLine = bufferedReader.readLine()) != null){
				jsonReturn += (inputLine + "\n");
			}
			
			bufferedReader.close();
			inputStreamReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return jsonReturn;
	}
}	
