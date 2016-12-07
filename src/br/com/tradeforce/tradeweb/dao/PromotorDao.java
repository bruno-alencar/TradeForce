package br.com.tradeforce.tradeweb.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

import br.com.tradeforce.tradeweb.model.Promotor;

@Repository
public class PromotorDao {
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void inserir(Promotor promotor){
		manager.persist(promotor);
	}
	
	public List<Promotor> listar(){
		EasyCriteria<Promotor> easyCriteria = EasyCriteriaFactory.createQueryCriteria(manager, Promotor.class);
		return easyCriteria.getResultList();
	}

	@Transactional
	public void excluir(Long idPromotor){
		Promotor promotor = manager.find(Promotor.class, idPromotor);
		manager.remove(promotor);
	}
	
	@Transactional
	public Promotor consultarPorId(Long id) {
		EasyCriteria<Promotor> easyCriteria = EasyCriteriaFactory.createQueryCriteria(manager, Promotor.class);
		easyCriteria.andEquals("id", id);
		return easyCriteria.getSingleResult();
	}
	
	@Transactional
	public void alterar(Promotor promotor){
		Promotor promotorAtualizar = manager.find(Promotor.class, promotor.getId());
		promotorAtualizar.setEmpresa(promotor.getEmpresa());
		promotorAtualizar.setIdade(promotor.getIdade());
		promotorAtualizar.setLocalizacao(promotor.getLocalizacao());
		promotorAtualizar.setLogin(promotor.getLogin());
		promotorAtualizar.setNome(promotor.getNome());
		promotorAtualizar.setSenha(promotor.getSenha());
		promotorAtualizar.setEndereco(promotor.getEndereco());
		manager.persist(promotorAtualizar);
	}
	
}
