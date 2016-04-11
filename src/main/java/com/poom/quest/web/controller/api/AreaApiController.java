package com.poom.quest.web.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.Area;

@Controller
@RequestMapping("api/area")
public class AreaApiController extends GenericApiController<Area> {
	
	private static final Logger logger = LoggerFactory.getLogger(AreaApiController.class);
	
	/**
	 * 싱위분야 리스트.
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Area> list() {
		return genericService.listByKey("parent", (Integer)null);
	}
}
