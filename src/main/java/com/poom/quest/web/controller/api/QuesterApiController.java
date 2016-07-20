package com.poom.quest.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.user.Quester;
import com.poom.quest.web.controller.api.generic.WithUserApiController;


@Controller
@RequestMapping("api/questers")
public class QuesterApiController extends WithUserApiController<Quester, Long> {
}
