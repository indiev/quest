package com.poom.quest.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.Area;
import com.poom.quest.web.controller.api.generic.TreeApiController;

@Controller
@RequestMapping("api/areas")
public class AreaApiController extends TreeApiController<Area, Long> {
}
