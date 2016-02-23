package com.poom.quest.web.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.abstractModel.GenericModel;
import com.poom.quest.services.service.GenericService;

@RequestMapping("api")
public abstract class GenericApiController<T> {

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired GenericService<T> genericService;
	
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public T get(@PathVariable Integer id) {
		return genericService.get(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<T> list() {
		return genericService.list();
	}
	
	@ResponseBody
	@RequestMapping(value = "/list/{keyword}", method = RequestMethod.GET)
	public List<T> list(@RequestParam String keyword, @RequestParam String[] keys) {
		return genericService.search(keyword, keys);
	}
	
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public T add(@RequestBody T entity, HttpServletRequest request) {
		return genericService.add(entity);
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public T update(@PathVariable Integer id, @RequestBody T entity) {
		((GenericModel)entity).setCreatedDate(((GenericModel)genericService.get(id)).getCreatedDate());
		return genericService.update(entity);
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Integer delete(@PathVariable Integer id) {
		return genericService.delete(id);
	}
}
