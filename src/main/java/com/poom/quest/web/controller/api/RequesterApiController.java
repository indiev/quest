package com.poom.quest.web.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poom.quest.services.model.user.Requester;
import com.poom.quest.web.controller.api.generic.WithUserApiController;


@RestController
@RequestMapping("api/requesters")
public class RequesterApiController extends WithUserApiController<Requester, Long>{
}
