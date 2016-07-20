package com.poom.quest.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.user.Requester;
import com.poom.quest.web.controller.api.generic.WithUserApiController;


@Controller
@RequestMapping("api/requesters")
public class RequesterApiController extends WithUserApiController<Requester, Long>{
}
