package com.poom.quest.services.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.domain.Requirement;

@Service
@Transactional
public class RequirementService extends GenericService<Requirement, Long> {
	
}
