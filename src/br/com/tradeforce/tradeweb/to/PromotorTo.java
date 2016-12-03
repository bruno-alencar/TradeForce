package br.com.tradeforce.tradeweb.to;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tradeforce.tradeweb.dao.PromotorDao;
import br.com.tradeforce.tradeweb.model.Promotor;

@Component
public class PromotorTo {
	@Autowired
	private PromotorDao promotorDao;
	

	public Promotor consultarPorId(Long id) {
		Promotor promotor = promotorDao.consultarPorId(id);
		return promotor;
	}
	
	public void inserir(Promotor promotor) {
		
		promotorDao.inserir(promotor);
	}
	
	public List<Promotor> listar() {
		return promotorDao.listar();
	}
	
	public void excluir(Long idPromotor) {
		promotorDao.excluir(idPromotor);
	}
	
	public void alterar(Promotor promotor) {
		promotorDao.alterar(promotor);
	}
	
}
