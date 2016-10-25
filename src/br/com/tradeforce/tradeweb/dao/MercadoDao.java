package br.com.tradeforce.tradeweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

import br.com.tradeforce.tradeweb.model.Mercado;

@Repository
public class MercadoDao {
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void inserir(Mercado mercado){
		manager.persist(mercado);
	}
	
	public List<Mercado> listar(){
		EasyCriteria<Mercado> easyCriteria = EasyCriteriaFactory.createQueryCriteria(manager, Mercado.class);
		return easyCriteria.getResultList();
	}
}
