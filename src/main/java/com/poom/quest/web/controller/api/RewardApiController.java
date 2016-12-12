package com.poom.quest.web.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poom.quest.services.domain.Reward;
import com.poom.quest.web.controller.api.generic.GenericApiController;

@RestController
@RequestMapping("api/rewards")
public class RewardApiController extends GenericApiController<Reward, Long> {
}
