package com.poom.quest.services.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.domain.user.Quester;

@Service
@Transactional
public class QuesterService extends GenericService<Quester, Long> {
	
}
