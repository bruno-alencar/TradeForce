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
	
	@Transactional
	public void excluir(Long idMercado){
		Mercado mercado = manager.find(Mercado.class, idMercado);
		manager.remove(mercado);
	}
	
	@Transactional
	public void atualizar(Mercado mercado){
		Mercado mercadoAtualizar = manager.find(Mercado.class, mercado.getId());
		mercadoAtualizar.setEndereco(mercado.getEndereco());
		mercadoAtualizar.setLocalizacao(mercado.getLocalizacao());
		mercadoAtualizar.setNome(mercado.getNome());
		mercadoAtualizar.setRazaoSocial(mercado.getRazaoSocial());
		manager.persist(mercadoAtualizar);
	}
	
	
	@Transactional
	public Mercado consultarPorId(Long id) {
		EasyCriteria<Mercado> easyCriteria = EasyCriteriaFactory.createQueryCriteria(manager, Mercado.class);
		easyCriteria.andEquals("id", id);
		return easyCriteria.getSingleResult();
	}
	
}
