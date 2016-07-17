package com.poom.quest.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.user.Requester;
import com.poom.quest.services.service.RequesterService;


@Controller
@RequestMapping("api/requesters")
public class RequesterApiController extends GenericApiController<Requester>{
	
	@Autowired RequesterService requesterService;
}
