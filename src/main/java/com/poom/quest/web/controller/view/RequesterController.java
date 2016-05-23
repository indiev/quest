package com.poom.quest.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.user.Requester;

@Controller
@RequestMapping("/requester")
public class RequesterController extends GenericViewController<Requester>{

}
