package com.poom.quest.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.Skill;
import com.poom.quest.web.controller.api.generic.GenericApiController;

@Controller
@RequestMapping("api/skills")
public class SkillApiController extends GenericApiController<Skill, Long> {
}
