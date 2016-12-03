package br.com.tradeforce.tradeweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

import br.com.tradeforce.tradeweb.model.Tarefa;

@Repository
public class TarefaDao {

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void inserir(Tarefa tarefa){
		manager.persist(tarefa);
	}
	
	@Transactional
	public Tarefa consultarPorPromotorId(Long idPromotor){
		
		EasyCriteria<Tarefa> easyCriteria = EasyCriteriaFactory.createQueryCriteria(manager, Tarefa.class);
		easyCriteria.andEquals("promotor", idPromotor);
		return easyCriteria.getSingleResult();
	}
	
	@Transactional
	public void excluir(Long idTarefa){
		Tarefa tarefa = manager.find(Tarefa.class, idTarefa);
		manager.remove(tarefa);
	}
	
	public List<Tarefa> listar() {
		EasyCriteria<Tarefa> easyCriteria = EasyCriteriaFactory.createQueryCriteria(manager,Tarefa.class);
		return easyCriteria.getResultList();
	}
	
	@Transactional
	public void alterar(Tarefa tarefa){
		Tarefa tarefaAtualizar = manager.find(Tarefa.class, tarefa.getId());
		tarefaAtualizar.setMercados(tarefa.getMercados());
		tarefaAtualizar.setPromotor(tarefa.getPromotor());
		tarefaAtualizar.setRotas(tarefa.getRotas());
		tarefaAtualizar.setRealizada(tarefa.isRealizada());
		tarefaAtualizar.setDataCriacao(tarefa.getDataCriacao());
		manager.persist(tarefaAtualizar);
	}
	
}
