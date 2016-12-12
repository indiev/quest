package com.poom.quest.web.contoller.api;

import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindException;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.support.SimpleSessionStatus;
import org.springframework.web.context.WebApplicationContext;

import com.poom.quest.services.domain.Area;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.AreaService;
import com.poom.quest.services.service.UserService;
import com.poom.quest.web.controller.api.generic.GenericApiController;

/*@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration*/
public class GenericControllerTest {
/*
	@Autowired private WebApplicationContext webApplicationContext;
	GenericApiController<Area, Long> controller;
	@Autowired AreaService mockService;
	@Autowired UserService userService;
	private String userName = "test";
	private User user;
	private MockMvc mockMvc;
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

	
	@Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
	
	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		this.user = new User();
		this.user.setName(userName);
		this.user.setPassword("password");
		userService.add(user);
		controller = new AreaApiController();
		mockService = mock(AreaService.class);
		controller.setService(mockService);
	}

	@Test
	public void getModel() throws Exception {
		long id = 1; 
		mockMvc.perform(get("/api/issue/"+mockService.get(id).getId()));
		Area area = new Area();
		area.setId(id);
		area.setName("1234");
		stub(mockService.get(id)).toReturn(area);
		assertThat(controller.get(id, null), sameInstance(area));
	}
	
	@Test
	public void getAll() {
		controller.list(null);
		verify(mockService.list());
	}
	
	@Test
	public void addPost() {
		Area area = new Area();
		SessionStatus status = new SimpleSessionStatus();
		BindException result = new BindException(area, "model");
		controller.add(area);
		verify(mockService).add(area);
	}

	@Test
	public void updatePost() {
		Area area = new Area();
		SessionStatus status = new SimpleSessionStatus();
		BindException result = new BindException(area, "model");
		HashMap<String, Object> model = new HashMap();
		model.put("abcd", "abcd");
		long id = 25;
		controller.update(id, model);
		verify(mockService).update(area);
	}*/
}
