package com.poom.quest.services.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.domain.Updater;

@Service
@Transactional
public class UpdaterService extends GenericService<Updater, Long> {
	
}
