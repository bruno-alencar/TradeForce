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

import br.com.tradeforce.tradeweb.model.Auxiliar;
import br.com.tradeforce.tradeweb.model.Localizacao;
import br.com.tradeforce.tradeweb.model.Mercado;
import br.com.tradeforce.tradeweb.model.Rota;

public class RotaTo {
	
	public List<Rota> gerarRotas(Auxiliar auxiliar, List<Localizacao>localizacoes){
		
		List<Rota> rotas = new ArrayList<Rota>();
		int sequencia = 0;
		
		for(int i = 0; i < localizacoes.size(); i++){
			List<Rota> rotasGeradas = new ArrayList<Rota>();
			
			if(i == 0){
				rotasGeradas = this.gerarCaminhos(auxiliar.getPromotor().getLocalizacao().getLatitude(),
						auxiliar.getPromotor().getLocalizacao().getLongitude(),
						localizacoes.get(i).getLatitude(),
						localizacoes.get(i).getLongitude());
				
//			}else if(i == localizacoes.size()-1){
//				rotasGeradas = this.gerarCaminhos(localizacoes.get(i).getLatitude(),
//						localizacoes.get(i).getLongitude(),
//						auxiliar.getPromotor().getLocalizacao().getLatitude(),
//						auxiliar.getPromotor().getLocalizacao().getLongitude());
//			}
			}else{
				rotasGeradas = this.gerarCaminhos(localizacoes.get(i-1).getLatitude(),
						localizacoes.get(i-1).getLongitude(),
						localizacoes.get(i).getLatitude(),
						localizacoes.get(i).getLongitude());
			}
			
			Rota rota = this.validarMenorPreco(rotasGeradas);
			
			rota.setSequencia(sequencia);
			
			rotas.add(rota);
			
			sequencia++;
		}
		
		return rotas;
		
	}
	
	public List<Mercado> ordernarMercados(List<Mercado> mercados, List<Localizacao> localizacoes){
		List<Mercado> mercadoOrdenado = new ArrayList<Mercado>();
		
		for(Localizacao localizacao : localizacoes){
			for(Mercado mercado: mercados){
				if(mercado.getLocalizacao().getLatitude() == localizacao.getLatitude()&&
						mercado.getLocalizacao().getLongitude() == localizacao.getLongitude()){
					mercadoOrdenado.add(mercado);
				}
			}
		}
		
		return mercadoOrdenado;
	}
	
	public List<Localizacao> tracarMelhorRotaWaypoints(Auxiliar auxiliar){
		String origin = auxiliar.getPromotor().getLocalizacao().getLatitude()+","+auxiliar.getPromotor().getLocalizacao().getLongitude();
		String destination = origin;
		String waypoints ="";
		
		for(Mercado mercado: auxiliar.getMercados()){
			waypoints += mercado.getLocalizacao().getLatitude() + "," + mercado.getLocalizacao().getLongitude() + "|";
		}
		
		waypoints = waypoints.substring(0, waypoints.length()-1);
		
		String link = "https://maps.googleapis.com/maps/api/directions/json?"
				+ "origin=" + origin
				+ "&destination=" + destination
				+ "&waypoints=" + waypoints
				+ "&key=AIzaSyCQetlePSuE-z8nGmpKh3NNLkzP_hHJiwk";
		
		System.out.println(link);
		
		String routes = this.conectar(link);
		List<Localizacao>localizacoes = new ArrayList<Localizacao>();
		
		try {
			JSONObject jobRoutes = new JSONObject(routes);
			
			JSONArray arrayRoutes = jobRoutes.getJSONArray("routes");
			
			for(int i = 0; i < arrayRoutes.length(); i++){
				JSONArray arrayLegs = arrayRoutes.getJSONObject(i).getJSONArray("legs");
				
				for(int j = 0; j <arrayLegs.length(); j++){
					Localizacao localizacao = new Localizacao();
					
					JSONObject jobEndLocation = arrayLegs.getJSONObject(j).getJSONObject("end_location");
					localizacao.setLatitude(jobEndLocation.getDouble("lat"));
					localizacao.setLongitude(jobEndLocation.getDouble("lng"));
					
					localizacoes.add(localizacao);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return localizacoes;
	}
	
	private Rota validarMenorPreco(List<Rota> rotas){
		
		Rota auxiliar = new Rota();
		auxiliar = rotas.get(0);
		
		for(int i = 1;i < rotas.size(); i ++){
			if(auxiliar.getPreco() > rotas.get(i).getPreco()){
				auxiliar = rotas.get(i);
			}
		}
		return auxiliar;
	}
	
	private List<Rota> gerarCaminhos(Double latitudeInicial, Double longitudeInicial, Double latitudeFinal, Double longitudeFinal){
		
		String origin = latitudeInicial+ "," +longitudeInicial;
		String destination = latitudeFinal+ "," +longitudeFinal;
		
		String link = "https://maps.googleapis.com/maps/api/directions/json?"
				+ "origin="+ origin
				+ "&destination="+ destination
				+ "&mode=transit&types=route&key=AIzaSyCQetlePSuE-z8nGmpKh3NNLkzP_hHJiwk";
		
		System.out.println(link);
		String routes = this.conectar(link);
		
		System.out.println(routes);
		
		
		List<Rota> rotas = new ArrayList<Rota>();
		try {
			JSONObject jobRoutes = new JSONObject(routes);

			JSONArray arrayRoutes = jobRoutes.getJSONArray("routes");

			for(int i = 0; i < arrayRoutes.length(); i ++){
				Rota rota = new Rota();
				
				if(arrayRoutes.getJSONObject(i).has("fare")){
					JSONObject jobFare = arrayRoutes.getJSONObject(i).getJSONObject("fare");
					rota.setPreco(jobFare.getDouble("value"));
				}
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
									System.out.println(arrayStepsPassoAPasso.getJSONObject(l).getString("html_instructions"));
									instrucoes.add(arrayStepsPassoAPasso.getJSONObject(l).getString("html_instructions"));
								}
								
								if(arrayStepsPassoAPasso.getJSONObject(l).has("polyline")){
									
									JSONObject jobStepsGeralPolylinePequeno = arrayStepsPassoAPasso.getJSONObject(l).getJSONObject("polyline");
									System.out.println(jobStepsGeralPolylinePequeno.getString("points"));
									polylines.add(jobStepsGeralPolylinePequeno.getString("points"));
								}
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
