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
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.tradeforce.tradeweb.dao.TarefaDao;
import br.com.tradeforce.tradeweb.model.Auxiliar;
import br.com.tradeforce.tradeweb.model.Rota;
import br.com.tradeforce.tradeweb.model.Tarefa;

@Component
public class TarefaTo {

	@Autowired
	TarefaDao tarefaDao;

	public void inserir(Auxiliar auxiliar){

		Tarefa tarefa = new Tarefa();
		tarefa.setPromotor(auxiliar.getPromotor());
		tarefa.setMercados(auxiliar.getMercados());		
		tarefa.setRotas(this.gerarRotas());
		
		tarefaDao.inserir(tarefa);
	}
	
	public List<Tarefa> listar(){
		return tarefaDao.listar();
	}
	
	public Tarefa consultarPorPromotorId(Long idPromotor){
		Tarefa tarefa = tarefaDao.consultarPorPromotorId(idPromotor);
		return tarefa;
	}
	
	public void excluir(@PathVariable("id") long idTarefa){
		tarefaDao.excluir(idTarefa);
	}
	
	
	
	public List<Rota> gerarRotas(){
		
		String routes = this.conectar("https://maps.googleapis.com/maps/api/directions/json?origin=Rio+Pequeno,SP&destination=Butanta,SP&mode=transit&types=route&key=AIzaSyCQetlePSuE-z8nGmpKh3NNLkzP_hHJiwk");
		System.out.println(routes);
		List<Rota> rotas = new ArrayList<Rota>();
		try {
			JSONObject jobRoutes = new JSONObject(routes);
			
			JSONArray arrayRoutes = jobRoutes.getJSONArray("routes");
			
			for(int i = 0; i < arrayRoutes.length(); i ++){
				Rota rota = new Rota();
				
				JSONObject jobFare = arrayRoutes.getJSONObject(i).getJSONObject("fare");
				rota.setPreco(jobFare.getDouble("value"));
				
				JSONArray arrayLegs = arrayRoutes.getJSONObject(i).getJSONArray("legs");
				
				for(int j = 0; j < arrayLegs.length(); j++){
					JSONArray arrayStepsGeral = arrayLegs.getJSONObject(j).getJSONArray("steps");
					List<String> instrucoes = new ArrayList<String>();
					List<String> polylines = new ArrayList<String>();
					
					for(int k = 0; k < arrayStepsGeral.length(); k++){
						instrucoes.add(arrayStepsGeral.getJSONObject(k).getString("html_instructions"));
						
						JSONObject jobStepsGeralPolyline = arrayStepsGeral.getJSONObject(k).getJSONObject("polyline");
						polylines.add(jobStepsGeralPolyline.getString("points"));
						
						if(arrayStepsGeral.getJSONObject(k).has("steps")){
							JSONArray arrayStepsPassoAPasso = arrayStepsGeral.getJSONObject(k).getJSONArray("steps");
						
							for(int l = 0; l < arrayStepsPassoAPasso.length(); l ++){
								if(arrayStepsPassoAPasso.getJSONObject(l).has("html_instructions")){
									instrucoes.add(arrayStepsPassoAPasso.getJSONObject(l).getString("html_instructions"));
								}
								JSONObject jobStepsGeralPolylinePequeno = arrayStepsGeral.getJSONObject(l).getJSONObject("polyline");
								polylines.add(jobStepsGeralPolylinePequeno.getString("points"));
							}
						}
					}
					
					rota.setInstrucoes(instrucoes);
					rota.setPolylines(polylines);
				}
				rotas.add(rota);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return rotas;
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
