package br.com.tradeforce.tradeweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

import br.com.tradeforce.tradeweb.model.Administrador;

@Repository
public class AdministradorDao {

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void inserir(Administrador administrador){
		manager.persist(administrador);
	}

	public List<Administrador> listar() {
		EasyCriteria<Administrador> easyCriteria = EasyCriteriaFactory.createQueryCriteria(manager, Administrador.class);
		easyCriteria.setDistinctTrue();
		return easyCriteria.getResultList();
	}
	
	@Transactional
	public Administrador consultarPorId(Long id) {
		EasyCriteria<Administrador> easyCriteria = EasyCriteriaFactory.createQueryCriteria(manager, Administrador.class);
		easyCriteria.andEquals("id", id);
		return easyCriteria.getSingleResult();
	}
	
	@Transactional
	public void excluir(Long idAdministrador) {
		Administrador administrador = manager.find(Administrador.class, idAdministrador);
		manager.remove(administrador);
	}
	
	@Transactional
	public void alterar(Administrador administrador){
		Administrador administradorAnterior = manager.find(Administrador.class, administrador.getId());	
		administradorAnterior.setNome(administrador.getNome());
		administradorAnterior.setLogin(administrador.getLogin());
		administradorAnterior.setSenha(administrador.getSenha());
		manager.persist(administradorAnterior);
	}
	
}
