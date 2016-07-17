package com.poom.quest.web.controller.api;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.Area;

@Controller
@RequestMapping("api/areas")
public class AreaApiController extends GenericApiController<Area> {
	
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Area> list() {
		return service.listByKeyId("parent", (Integer)null);
	}
}
