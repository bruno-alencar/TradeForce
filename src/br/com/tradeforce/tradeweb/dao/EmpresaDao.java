package br.com.tradeforce.tradeweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

import br.com.tradeforce.tradeweb.model.Empresa;

@Repository
public class EmpresaDao {

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void inserir(Empresa empresa) {
		manager.persist(empresa);
	}

	public List<Empresa> listar() {
		EasyCriteria<Empresa> easyCriteria = EasyCriteriaFactory.createQueryCriteria(manager, Empresa.class);
		return easyCriteria.getResultList();
	}
	
	@Transactional
	public Empresa consultarPorEmpresaId(Long id) {
		EasyCriteria<Empresa> easyCriteria = EasyCriteriaFactory.createQueryCriteria(manager, Empresa.class);
		easyCriteria.andEquals("id", id);
		return easyCriteria.getSingleResult();
	}
	
	@Transactional
	public void excluir(Long idEmpresa) {
		Empresa empresa = manager.find(Empresa.class, idEmpresa);
		manager.remove(empresa);
	}
	
	@Transactional
	public void alterar(Empresa empresa){
		Empresa empresaAnterior = manager.find(Empresa.class, empresa.getId());	
		empresaAnterior.setNome(empresa.getNome());
		empresaAnterior.setRazaoSocial(empresa.getRazaoSocial());
		empresaAnterior.setCnpj(empresa.getCnpj());;
		manager.merge(empresaAnterior);
	}
	
}
