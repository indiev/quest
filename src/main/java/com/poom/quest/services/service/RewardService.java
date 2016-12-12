package com.poom.quest.services.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.domain.Reward;

@Service
@Transactional
public class RewardService extends GenericService<Reward, Long> {
	
}
