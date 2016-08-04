package com.poom.quest.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.Reward;
import com.poom.quest.web.controller.api.generic.GenericApiController;

@Controller
@RequestMapping("api/rewards")
public class RewardApiController extends GenericApiController<Reward, Long> {
}
