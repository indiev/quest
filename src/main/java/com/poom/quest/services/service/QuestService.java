package com.poom.quest.services.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.poom.quest.services.model.Quest;

@Service
@Transactional
public class QuestService extends GenericService<Quest> {
	
}
