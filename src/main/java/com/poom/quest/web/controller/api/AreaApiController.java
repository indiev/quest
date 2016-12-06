package com.poom.quest.web.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poom.quest.services.model.Area;
import com.poom.quest.web.controller.api.generic.TreeApiController;

@RestController
@RequestMapping("api/areas")
public class AreaApiController extends TreeApiController<Area, Long> {
}
