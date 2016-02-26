package com.poom.quest.web.controller.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.Area;
import com.poom.quest.services.model.Kind;

@Controller
@RequestMapping("api/kind")
public class KindApiController extends GenericApiController<Kind> {
	
	private static final Logger logger = LoggerFactory.getLogger(KindApiController.class);
	
	@ResponseBody
	@RequestMapping(value = "/area/{parentId}", method = RequestMethod.GET)
	public List<Kind> listByParent(@PathVariable Integer parentId) {
		return genericService.listByParent(parentId, Area.class);
	}
}
