package com.poom.quest.services.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.domain.Provision;

@Service
@Transactional
public class ProvisionService extends GenericService<Provision, Long> {
	
}
