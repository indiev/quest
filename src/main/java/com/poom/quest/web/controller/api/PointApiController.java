package com.poom.quest.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.log.PointLog;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.PointService;
import com.poom.quest.services.service.UserService;


@Controller
@RequestMapping("api/points")
public class PointApiController {
	
	@Autowired PointService pointService;
	@Autowired UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/deposit/{rewardId}", method = RequestMethod.PUT)
	public PointLog deposit(@PathVariable("rewardId") Long rewardId) {
		User user = userService.getLoginUserByRequest();
		if(user !=null ) return pointService.deposit(rewardId, user);
		else return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/withdraw/{rewardId}", method = RequestMethod.PUT)
	public PointLog withdraw(@PathVariable("rewardId") Long rewardId) {
		User user = userService.getLoginUserByRequest();
		if(user !=null ) return pointService.withdraw(rewardId, user);
		else return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/give/{rewardId}", method = RequestMethod.PUT)
	public PointLog give(@PathVariable("rewardId") Long rewardId) {
		User user = userService.getLoginUserByRequest();
		if(user !=null ) return pointService.give(rewardId, user);
		else return null;
	}

}
