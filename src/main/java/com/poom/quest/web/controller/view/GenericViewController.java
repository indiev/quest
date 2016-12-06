package com.poom.quest.web.controller.view;

import java.lang.reflect.ParameterizedType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poom.quest.services.model.abstractModel.Domain;
import com.poom.quest.services.service.GenericService;
import com.poom.quest.services.service.UserService;

public abstract class GenericViewController<T extends Domain> {

	@Autowired GenericService<T, Long> genericService;
	@Autowired private UserService userService;
	
	@SuppressWarnings("unchecked")
	protected final Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	protected final String model = clazz.getSimpleName();
	protected final String modelName = model.substring(0, 1).toLowerCase() + model.substring(1);
	
	@RequestMapping(value = "/template/top", method = RequestMethod.GET)
	public String top() {
		return "top";
	}
	
	@RequestMapping(value = "/template/bottom", method = RequestMethod.GET)
	public String bottom() {
		return "bottom";
	}
	
	@RequestMapping(value = "/template/left", method = RequestMethod.GET)
	public String left() {
		return "/template/leftSider";
	}
	
	@RequestMapping(value = "/template/right", method = RequestMethod.GET)
	public String right(Model model) {
		model.addAttribute("user", userService.getLoginUserByRequest());
		return "/template/rightSider";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String main(Model model) {
		return modelName + "/main";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return modelName + "/add";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return modelName + "/list";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable Long id) {
		return modelName + "/detail";
	}
	
	@RequestMapping(value = "/node/{view}", method = RequestMethod.GET)
	public String node(@PathVariable String view) {
		return modelName + "/node/" + view;
	}
}
