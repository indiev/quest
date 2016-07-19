package com.poom.quest.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.Reward;

@Controller
@RequestMapping("api/rewards")
public class RewardApiController extends GenericApiController<Reward, Long> {
}
