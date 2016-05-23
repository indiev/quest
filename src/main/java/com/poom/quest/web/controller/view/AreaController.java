package com.poom.quest.web.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poom.quest.services.model.Area;

@Controller
@RequestMapping("/area")
public class AreaController extends GenericViewController<Area>{ /* 로그인 하지 않은 사용자를 대상으로 한 view 컨트롤러 */
	
	@RequestMapping(value = "node/list", method = RequestMethod.GET)
	public String listNode(HttpServletRequest request, Model model) {
		return modelName + "/node/list";
	}
	
}
