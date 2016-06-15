package com.poom.quest.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.model.Code;
import com.poom.quest.services.model.Portfolio;
import com.poom.quest.services.repository.PortfolioRepository;

@Service
@Transactional
public class PortfolioService extends GenericService<Portfolio> {
	
	@Autowired CodeService codeService;
	@Autowired PortfolioRepository portfolioDao;
	
	public List<Portfolio> searchByType(String typeValue, String keyword, Integer userId) {
		Code type = codeService.getByKey("value", typeValue);
		return portfolioDao.searchByType(type.getId(), keyword, userId);
	}
}
