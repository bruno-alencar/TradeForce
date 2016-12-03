package br.com.tradeforce.tradeweb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tradeforce.tradeweb.model.Tarefa;
import br.com.tradeforce.tradeweb.to.RelatorioTo;

@RestController
public class RelatorioController {
	
	@Autowired
	private RelatorioTo relatorioTo;
	
	@RequestMapping(value="/relatorio/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String gerarRelatorio(@PathVariable("id") Long idPromotor){
		List<String> relatorio = new ArrayList<String>();
//		relatorio = this.relatorioTo.gerarRelatorio(idPromotor);
		
		JSONArray arrayRelatorio = new JSONArray();
		
		try {
			
			for(int i = 0; i < relatorio.size();i+=3){
				JSONObject jobRelatorio = new JSONObject();
				jobRelatorio.put("data", relatorio.get(i));
				jobRelatorio.put("valorTotal", Double.parseDouble(relatorio.get(i+1)));
				jobRelatorio.put("quantidade", Integer.parseInt(relatorio.get(i+2)));
				
				arrayRelatorio.put(jobRelatorio);
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return arrayRelatorio.toString();
		
	};
	
	public void gerarRelatorioPorPromotor(){
		
	}
}
