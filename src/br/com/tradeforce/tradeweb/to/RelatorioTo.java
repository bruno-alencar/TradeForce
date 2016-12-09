package br.com.tradeforce.tradeweb.to;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tradeforce.tradeweb.dao.RelatorioDao;
import br.com.tradeforce.tradeweb.model.Relatorio;
import br.com.tradeforce.tradeweb.model.Rota;
import br.com.tradeforce.tradeweb.model.Tarefa;

@Component
public class RelatorioTo {
	
	@Autowired
	private RelatorioDao relatorioDao;
	
	public List<Relatorio> gerarRelatorio(Long idPromotor){
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		tarefas = relatorioDao.gerarRelatorio(idPromotor);
		
		List<Relatorio> relatorios = new ArrayList<Relatorio>();
		
		Double soma = 0.0;
		int quantidade = 0;
		
		for(Tarefa tarefa: tarefas){
			Relatorio relatorio = new Relatorio();
			
			relatorio.setIdTarefa(tarefa.getId());
			relatorio.setData(tarefa.getDataCriacao());
			
			for(Rota rota: tarefa.getRotas()){
				if(rota.getPreco()!= null){
					soma += rota.getPreco();
					quantidade++;	
				}
			}
			relatorio.setValorTotal(soma);
			relatorio.setQuantidadeMercados(quantidade);
			
			soma = 0.0;
			quantidade = 0;
			relatorios.add(relatorio);
		}
		
		return relatorios;
	}
}
