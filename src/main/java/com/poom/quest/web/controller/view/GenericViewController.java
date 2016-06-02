package com.poom.quest.web.controller.view;

import java.lang.reflect.ParameterizedType;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poom.quest.services.service.GenericService;
import com.poom.quest.services.service.UserService;

@RequestMapping("/")
public abstract class GenericViewController<T> {

	@Autowired GenericService<T> genericService;
	@Autowired private UserService userService;
	
	@SuppressWarnings("unchecked")
	protected final Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	protected final String modelName = clazz.getSimpleName().toLowerCase();
	
	@RequestMapping(value = "/template/top", method = RequestMethod.GET)
	public String top(Model model) {
		return "left";
	}
	
	@RequestMapping(value = "/template/bottom", method = RequestMethod.GET)
	public String bottom(Model model) {
		return "left";
	}
	
	@RequestMapping(value = "/template/left", method = RequestMethod.GET)
	public String left(Model model) {
		return "/template/leftSider";
	}
	
	@RequestMapping(value = "/template/right", method = RequestMethod.GET)
	public String right(HttpServletRequest request, Model model) {
		model.addAttribute("user", userService.getLoginUserByRequest(request));
		return "/template/rightSider";
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return modelName + "/main";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request) {
		return modelName + "/list";
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String detail(@PathVariable Integer id, HttpServletRequest request) {
		return modelName + "/detail";
	}
	
	@RequestMapping(value = "node/{view}", method = RequestMethod.GET)
	public String node(@PathVariable String view, HttpServletRequest request) {
		return modelName + "/node/" + view;
	}
}
