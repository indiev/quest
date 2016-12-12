package com.poom.quest.web.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poom.quest.services.domain.Skill;
import com.poom.quest.web.controller.api.generic.GenericApiController;

@RestController
@RequestMapping("api/skills")
public class SkillApiController extends GenericApiController<Skill, Long> {
}
