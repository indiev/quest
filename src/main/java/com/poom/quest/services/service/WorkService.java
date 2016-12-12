package com.poom.quest.services.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.domain.Work;;

@Service
@Transactional
public class WorkService extends GenericService<Work, Long> {
	
}
