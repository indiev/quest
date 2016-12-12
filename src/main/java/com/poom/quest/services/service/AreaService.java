package com.poom.quest.services.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.domain.Area;;

@Service
@Transactional
public class AreaService extends GenericService<Area, Long> {
	
}
