package com.poom.quest.services.repository;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.poom.quest.services.model.Portfolio;

@Repository
public class PortfolioRepository extends GenericRepository<Portfolio, Long> {
	
	public List<Portfolio> searchByType(Long typeId, String keyword, Long userId) {
		keyword = ("%" + keyword + "%").toLowerCase();
		String where = " WHERE typeId = :typeId AND userId = :userId";
		where += " AND LOWER(name) LIKE :keyword";
		Query query = em.createNativeQuery(SELECT_ALL_SQL + where, clazz).setParameter("typeId", typeId).setParameter("userId", userId).setParameter("keyword", "%" + keyword + "%");
		return query.getResultList();
	}

}
