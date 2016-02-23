package com.poom.quest.services.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.poom.quest.services.model.user.Requester;

@Service
@Transactional
public class RequesterService extends GenericService<Requester> {
	
}
