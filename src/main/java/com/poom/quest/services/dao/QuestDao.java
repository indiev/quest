package com.poom.quest.services.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.poom.quest.services.model.Quest;

@Repository
public class QuestDao extends GenericDao<Quest> {

	public List<Quest> questsOfQuester(Integer questerId, Integer stateId) {
		String query = SELECT_ALL_SQL + " JOIN QuesterQuest on QuesterQuest.questId = Quest.id and QuesterQuest.questerId = :questerId";
		if(stateId != null) query += " where stateId = :stateId";
		return em.createNativeQuery(query, clazz).setParameter("questerId", questerId).setParameter("stateId", stateId).getResultList();
	}

	public List<Quest> searchByState(Integer stateId, String keyword) {
		String where = " WHERE stateId = :stateId";
		
		if(keyword != null) where += " AND LOWER(name) LIKE :keyword";
		Query query = em.createNativeQuery(SELECT_ALL_SQL + where, clazz).setParameter("stateId", stateId);
		if(keyword != null) query.setParameter("keyword", "%" + keyword + "%");
		return query.getResultList();
	}
}