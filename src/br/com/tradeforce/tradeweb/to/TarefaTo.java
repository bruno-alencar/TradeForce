package br.com.tradeforce.tradeweb.to;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.tradeforce.tradeweb.dao.TarefaDao;
import br.com.tradeforce.tradeweb.model.Auxiliar;
import br.com.tradeforce.tradeweb.model.Tarefa;

@Component
public class TarefaTo {

	@Autowired
	private TarefaDao tarefaDao;

	private RotaTo rotaTo;

	public void inserir(Auxiliar auxiliar){

		Tarefa tarefa = new Tarefa();
		tarefa.setPromotor(auxiliar.getPromotor());
		tarefa.setMercados(auxiliar.getMercados());		
		tarefa.setRotas(rotaTo.gerarRotas(auxiliar));

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

}

