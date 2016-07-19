package com.poom.quest.services.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.model.Contract;

@Service
@Transactional
public class ContractService extends GenericService<Contract, Long> {
	
}
