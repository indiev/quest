package com.poom.quest.services.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.domain.user.Applicant;

@Service
@Transactional
public class ApplicantService extends GenericService<Applicant, Long> {
	
}
