package com.poom.quest.web.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.GenericService;
import com.poom.quest.services.service.UserService;

@RequestMapping("api")
public abstract class GenericApiController<T> {

	@Autowired GenericService<T> genericService;
	@Autowired UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<T> list() {
		return genericService.list();
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public T get(@PathVariable Integer id) {
		return genericService.get(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<T> listByUser(HttpServletRequest request) {
		User user = userService.getLoginUserByRequest(request);
		if(user != null) return genericService.listByKeyId("user", user.getId());
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/{keyname}/{key}",  method = RequestMethod.GET)
	public List<T> listByUserAndKey(@PathVariable("keyname") String keyName, @PathVariable("key") String key, HttpServletRequest request) {
		Map<String, Object> keys = new HashMap<>();
		User user = userService.getLoginUserByRequest(request);
		if(user != null) {
			keys.put("userId", user.getId());
			keys.put("typeId", key);
			return genericService.listByKeys(keys);
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ref/{keyname}/{key}",  method = RequestMethod.GET)
	public List<T> listByReferenceKey(@PathVariable("keyname") String keyName, @PathVariable("key") String key) {
		return genericService.listByKeyId(keyName, key);
	}
	
	@ResponseBody
	@RequestMapping(value = "/{keyname}/{key}",  method = RequestMethod.GET)
	public List<T> listByKey(@PathVariable("keyname") String keyName, @PathVariable("key") String key) {
		return genericService.listByKey(keyName, key);
	}
	
	@ResponseBody
	@RequestMapping(value = "/search/{keyword}", method = RequestMethod.GET)
	public List<T> search(@PathVariable String keyword/*, @RequestParam String[] keys*/) {
		String[] keys = new String[] { "name" };
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
		return genericService.update(entity);
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Integer delete(@PathVariable Integer id) {
		return genericService.delete(id);
	}
}
