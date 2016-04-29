package com.poom.quest.services.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.poom.quest.services.model.Quest;

@Repository
public class QuestDao extends GenericDao<Quest> {

	public List<Quest> questsOfQuester(Integer questerId, Integer stateId) {
		String query = SELECT_ALL_SQL + "JOIN QuesterQuest on QuesterQuest.questId = Quest.id and QuesterQuest.questerId = :questerId";
		if(stateId != null) query += " where stateId = :stateId";
		return em.createNativeQuery(query, clazz).setParameter("questerId", questerId).setParameter("stateId", stateId).getResultList();
	}
}
