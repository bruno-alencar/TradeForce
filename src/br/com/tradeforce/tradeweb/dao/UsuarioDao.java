package br.com.tradeforce.tradeweb.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

import br.com.tradeforce.tradeweb.model.Usuario;


@Repository
public class UsuarioDao {
	@PersistenceContext
	private EntityManager manager;
	
	public Usuario logar(Usuario usuario){
		
		EasyCriteria<Usuario> easyCriteria = EasyCriteriaFactory.createQueryCriteria(manager, Usuario.class);
		easyCriteria.andEquals("login", usuario.getLogin());
		easyCriteria.andEquals("senha", usuario.getSenha());
		try {
			return easyCriteria.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
