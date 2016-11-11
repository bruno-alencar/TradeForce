package br.com.tradeforce.tradeweb.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tradeforce.tradeweb.model.Localizacao;

@Repository
public class LocalizacaoDao {
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void inserir(Localizacao localizacao){
		manager.persist(localizacao);
	}
	
}
