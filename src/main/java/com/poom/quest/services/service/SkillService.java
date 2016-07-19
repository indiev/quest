package com.poom.quest.services.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.model.Skill;

@Service
@Transactional
public class SkillService extends GenericService<Skill, Long> {
	
}
