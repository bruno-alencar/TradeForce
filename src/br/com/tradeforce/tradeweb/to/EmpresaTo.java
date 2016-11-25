package br.com.tradeforce.tradeweb.to;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tradeforce.tradeweb.dao.EmpresaDao;
import br.com.tradeforce.tradeweb.model.Empresa;

@Component
public class EmpresaTo {
	
	@Autowired
	private EmpresaDao empresaDao;
	
	public void inserir(Empresa empresa) {
		empresaDao.inserir(empresa);
	}

	public List<Empresa> listar() {
		return empresaDao.listar();
	}

	public Empresa consultarPorId(Long idEmpresa) {
		Empresa empresa = empresaDao.consultarPorEmpresaId(idEmpresa);
		return empresa;
	}

	public void excluir(Long idEmpresa) {
		empresaDao.excluir(idEmpresa);
	}

	public void alterar(Empresa empresa) {
		empresaDao.alterar(empresa);
	}
	
}
