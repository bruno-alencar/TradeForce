package br.com.tradeforce.tradeweb.to;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tradeforce.tradeweb.dao.TarefaDao;
import br.com.tradeforce.tradeweb.model.Auxiliar;
import br.com.tradeforce.tradeweb.model.Localizacao;
import br.com.tradeforce.tradeweb.model.Mercado;
import br.com.tradeforce.tradeweb.model.Rota;
import br.com.tradeforce.tradeweb.model.Tarefa;

@Component
public class TarefaTo {

	@Autowired
	private TarefaDao tarefaDao;

	private RotaTo rotaTo = new RotaTo();

	public void inserir(Auxiliar auxiliar){
		
		List<Localizacao> localizacoes = new ArrayList<Localizacao>();
		List<Mercado> mercados = new ArrayList<Mercado>();
		List<Rota> rotas = new ArrayList<Rota>();
		
		localizacoes = rotaTo.tracarMelhorRotaWaypoints(auxiliar);
		mercados = rotaTo.ordernarMercados(auxiliar.getMercados(), localizacoes);
		rotas = rotaTo.gerarRotas(auxiliar,localizacoes);
		
		Tarefa tarefa = new Tarefa();
		tarefa.setPromotor(auxiliar.getPromotor());
		tarefa.setMercados(mercados);	
		tarefa.setRotas(rotas);

		tarefaDao.inserir(tarefa);
	}
	
	public List<Tarefa> listar(){
		return tarefaDao.listar();
	}

	public Tarefa consultarPorPromotorId(Long idPromotor){
		Tarefa tarefa = tarefaDao.consultarPorPromotorId(idPromotor);
		return tarefa;
	}

	public void excluir(Long idTarefa){
		tarefaDao.excluir(idTarefa);
	}
	
}

