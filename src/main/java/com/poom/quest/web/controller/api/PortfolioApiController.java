package com.poom.quest.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.Portfolio;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.PortfolioService;

@Controller
@RequestMapping("api/portfolio")
public class PortfolioApiController extends GenericApiController<Portfolio> {
	
	@Autowired PortfolioService portfolioService;
	
	@Override
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Portfolio add(@RequestBody Portfolio entity) {
		User user = userService.getLoginUserByRequest();
		if(user != null) {
			entity.setUser(user);
			return genericService.add(entity);
		}
		return null;
	}

}
