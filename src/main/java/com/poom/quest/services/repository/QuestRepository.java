package com.poom.quest.services.repository;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.poom.quest.services.model.Quest;

@Repository
public class QuestRepository extends GenericRepository<Quest, Long> {

	public List<Quest> questsOfQuester(Long questerId, Long stateId) {
		String query = SELECT_ALL_SQL + " JOIN QuesterQuest on QuesterQuest.questId = Quest.id and QuesterQuest.questerId = :questerId";
		if(stateId != null) query += " where stateId = :stateId";
		return em.createNativeQuery(query, domainClass).setParameter("questerId", questerId).setParameter("stateId", stateId).getResultList();
	}
	
	public List<Quest> questsOfApplicant(Long questerId, Long stateId) {
		String query = SELECT_ALL_SQL + " JOIN ApplicantQuest on ApplicantQuest.questId = Quest.id and ApplicantQuest.questerId = :questerId";
		if(stateId != null) query += " where stateId = :stateId";
		return em.createNativeQuery(query, domainClass).setParameter("questerId", questerId).setParameter("stateId", stateId).getResultList();
	}
	
	public List<Quest> questsOfApplicant(Long questerId, List<Long> stateIds) {
		String query = SELECT_ALL_SQL + " JOIN ApplicantQuest on ApplicantQuest.questId = Quest.id and ApplicantQuest.questerId = :questerId";
		if(stateIds != null && stateIds.size() > 0) {
			query += " where stateId in :stateIds";
		}
		return em.createNativeQuery(query, domainClass).setParameter("questerId", questerId).setParameter("stateIds", stateIds).getResultList();
	}
	
	public List<Quest> questsOfRequester(Long requesterId, Long stateId) {
		String where = " WHERE requesterId = :requesterId AND stateId = :stateId";
		Query query = em.createNativeQuery(SELECT_ALL_SQL + where, domainClass).setParameter("requesterId", requesterId).setParameter("stateId", stateId);
		return query.getResultList();
	}

	public List<Quest> questsOfRequester(Long requesterId, List<Long> stateIds) {
		String query = SELECT_ALL_SQL + " WHERE requesterId = :requesterId";
		if(stateIds != null && stateIds.size() > 0) {
			query += " AND stateId in :stateIds";
		}
		return em.createNativeQuery(query, domainClass).setParameter("requesterId", requesterId).setParameter("stateIds", stateIds).getResultList();
	}

	public List<Quest> searchByState(Long stateId, String keyword) {
		keyword = "%" + keyword + "%";
		String where = " WHERE stateId = :stateId";
		where += " AND LOWER(name) LIKE :keyword";
		Query query = em.createNativeQuery(SELECT_ALL_SQL + where, domainClass).setParameter("stateId", stateId).setParameter("keyword", "%" + keyword + "%");
		return query.getResultList();
	}


}