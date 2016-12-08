package br.com.tradeforce.tradeweb.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tradeforce.tradeweb.dao.MercadoDao;
import br.com.tradeforce.tradeweb.dao.PromotorDao;
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
	
	@Autowired
	private MercadoDao mercadoDao;
	
	@Autowired
	private PromotorDao promotorDao;

	private RotaTo rotaTo = new RotaTo();

	public void inserir(Auxiliar auxiliar){
		
		List<Localizacao> localizacoes = new ArrayList<Localizacao>();
		List<Mercado> mercados = new ArrayList<Mercado>();
		List<Rota> rotas = new ArrayList<Rota>();
		
		localizacoes = rotaTo.tracarMelhorRotaWaypoints(auxiliar);
		//localizacoes.add(auxiliar.getPromotor().getLocalizacao());
		
//		mercados = rotaTo.ordernarMercados(auxiliar.getMercados(), localizacoes);
		rotas = rotaTo.gerarRotas(auxiliar,localizacoes);
		
		Tarefa tarefa = new Tarefa();
		tarefa.setPromotor(auxiliar.getPromotor());
//		tarefa.setMercados(mercados);	
		tarefa.setMercados(auxiliar.getMercados());
		tarefa.setRotas(rotas);
		tarefa.setDataCriacao(new Date(System.currentTimeMillis()));
		
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
	
	public void alterar(Tarefa tarefa){
		tarefaDao.alterar(tarefa);
	}
	
}

