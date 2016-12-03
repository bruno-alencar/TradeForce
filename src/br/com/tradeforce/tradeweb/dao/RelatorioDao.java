package br.com.tradeforce.tradeweb.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

import br.com.tradeforce.tradeweb.model.Tarefa;

@Repository
public class RelatorioDao {
		
	@PersistenceContext
	private EntityManager manager;
	
	public List<Tarefa> gerarRelatorio(Long idPromotor){
		EasyCriteria<Tarefa> easyCriteriaPromotor = EasyCriteriaFactory.createQueryCriteria(manager, Tarefa.class);
		easyCriteriaPromotor.andEquals("promotor", idPromotor);
		return easyCriteriaPromotor.getResultList();
	}
}
