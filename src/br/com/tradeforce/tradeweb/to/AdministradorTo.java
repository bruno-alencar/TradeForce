package br.com.tradeforce.tradeweb.to;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tradeforce.tradeweb.dao.AdministradorDao;
import br.com.tradeforce.tradeweb.model.Administrador;

@Component
public class AdministradorTo {
	@Autowired
	private AdministradorDao administradorDao;
	
	public void inserir(Administrador administrador) {
		administradorDao.inserir(administrador);
	}

	public List<Administrador> listar() {
		return administradorDao.listar();
	}

	public Administrador consultarPorId(Long idAdministrador) {
		Administrador empresa = administradorDao.consultarPorId(idAdministrador);
		return empresa;
	}

	public void excluir(Long idAdministrador) {
		administradorDao.excluir(idAdministrador);
	}

	public void alterar(Administrador administrador) {
		administradorDao.alterar(administrador);
	}
}
