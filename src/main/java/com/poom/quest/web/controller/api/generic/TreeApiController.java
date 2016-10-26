package com.poom.quest.web.controller.api.generic;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poom.quest.services.model.abstractModel.TreeModel;

@RequestMapping("api")
public abstract class TreeApiController<T extends TreeModel<T>, ID> extends GenericApiController<T, ID> {
	@RequestMapping("/parents")
	public List<T> parents(@RequestParam Map<String, Object> params) {
		return getService().listByParent((ID)null, domainClass);
	}
}
