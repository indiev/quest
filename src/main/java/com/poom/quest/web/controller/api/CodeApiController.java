package com.poom.quest.web.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.Code;

@Controller
@RequestMapping("api/codes")
public class CodeApiController extends GenericApiController<Code> {

	@ResponseBody
	@RequestMapping(value = "/{model}/{attribute}",  method = RequestMethod.GET)
	public List<Code> listByKey(@PathVariable("model") String model, @PathVariable("attribute") String attribute) {
		Map<String, Object> keys = new HashMap<String, Object>();
		keys.put("model", model);
		keys.put("attribute", attribute);
		return service.listByKeys(keys);
	}
}
