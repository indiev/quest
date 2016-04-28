package com.poom.quest.web.controller.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poom.quest.services.model.user.Requester;

@Controller
@RequestMapping("/requester")
public class RequesterController extends GenericViewController<Requester>{
	
	private static final Logger logger = LoggerFactory.getLogger(RequesterController.class);

	

}
