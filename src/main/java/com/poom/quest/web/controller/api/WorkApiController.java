package com.poom.quest.web.controller.api;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.Work;
import com.poom.quest.web.controller.api.generic.TreeApiController;

@Controller
@RequestMapping("api/works")
public class WorkApiController extends TreeApiController<Work, Long> {
}
