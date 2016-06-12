package com.poom.quest.services.repository;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.poom.quest.services.model.Portfolio;

@Repository
public class PortfolioRepository extends GenericRepository<Portfolio> {
	
	public List<Portfolio> searchByState(Integer typeId, String keyword, Integer userId) {
		keyword = "%" + keyword + "%";
		String where = " WHERE typeId = :typeId AND userId = :userId";
		where += " AND LOWER(name) LIKE :keyword";
		Query query = em.createNativeQuery(SELECT_ALL_SQL + where, clazz).setParameter("typeId", typeId).setParameter("userId", userId).setParameter("keyword", "%" + keyword + "%");
		return query.getResultList();
	}

}
