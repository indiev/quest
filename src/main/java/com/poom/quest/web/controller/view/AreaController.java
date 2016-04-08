package com.poom.quest.web.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poom.quest.services.model.Area;
import com.poom.quest.services.model.user.User;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/area")
public class AreaController extends GenericViewController<Area>{ /* 로그인 하지 않은 사용자를 대상으로 한 view 컨트롤러 */
	
	private static final Logger logger = LoggerFactory.getLogger(AreaController.class);
	
	@RequestMapping(value = "node/list", method = RequestMethod.GET)
	public String listNode(HttpServletRequest request, Model model) {
		return modelName + "/node/list";
	}
	
}
