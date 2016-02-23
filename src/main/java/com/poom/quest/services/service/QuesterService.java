package com.poom.quest.services.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.poom.quest.services.model.user.Quester;

@Service
@Transactional
public class QuesterService extends GenericService<Quester> {
	
}
