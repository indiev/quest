package com.poom.quest.services.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.model.user.Requester;

@Service
@Transactional
public class RequesterService extends GenericService<Requester, Long> {
	
}
