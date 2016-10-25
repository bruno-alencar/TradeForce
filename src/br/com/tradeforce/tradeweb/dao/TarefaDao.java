package br.com.tradeforce.tradeweb.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tradeforce.tradeweb.model.Tarefa;

@Repository
public class TarefaDao {

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void inserir(Tarefa tarefa){
		manager.persist(tarefa);
	}

}
