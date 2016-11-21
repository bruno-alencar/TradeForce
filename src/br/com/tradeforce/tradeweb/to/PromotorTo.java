package br.com.tradeforce.tradeweb.to;

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
	
}
