package com.poom.quest.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poom.quest.services.domain.Portfolio;
import com.poom.quest.services.domain.user.User;
import com.poom.quest.services.service.PortfolioService;
import com.poom.quest.web.controller.api.generic.WithUserApiController;

@RestController
@RequestMapping("api/portfolios")
public class PortfolioApiController extends WithUserApiController<Portfolio, Long> {
	
	/*
	@Autowired PortfolioService portfolioService;
	
	@ResponseBody
	@RequestMapping(value = "user/search", method = RequestMethod.GET)
	public List<Portfolio> allSearch() {
		return search("");
	}
	
	@ResponseBody
	@RequestMapping(value = "user/{typeValue}/search", method = RequestMethod.GET)
	public List<Portfolio> allSearchByTypeValue(@PathVariable String typeValue) {
		User user = userService.getLoginUserByRequest();
		if(user != null) {
			return portfolioService.searchByType(typeValue, "", user.getId());
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "user/search/{keyword}", method = RequestMethod.GET)
	public List<Portfolio> search(@PathVariable String keyword) {
		User user = userService.getLoginUserByRequest();
		if(user != null) {
			return service.search(keyword, null, user.getId());
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "user/{typeValue}/search/{keyword}", method = RequestMethod.GET)
	public List<Portfolio> searchByTypeValue(@PathVariable("typeValue") String typeValue, @PathVariable("keyword") String keyword) {
		User user = userService.getLoginUserByRequest();
		if(user != null) {
			return portfolioService.searchByType(typeValue, keyword, user.getId());
		}
		return null;
	}*/
}
