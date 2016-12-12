package com.poom.quest.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.domain.Code;
import com.poom.quest.services.domain.Portfolio;
import com.poom.quest.services.repository.PortfolioRepository;

@Service
@Transactional
public class PortfolioService extends GenericService<Portfolio, Long> {
	
	@Autowired PortfolioRepository portfolioDao;
	@Autowired CodeService codeService;
	
	public List<Portfolio> searchByType(String typeValue, String keyword, Long userId) {
		Code type = codeService.getByKey("value", typeValue);
		return portfolioDao.searchByType(type.getId(), keyword, userId);
	}
}
