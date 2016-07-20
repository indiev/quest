package com.poom.quest.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.Updater;
import com.poom.quest.web.controller.api.generic.WithUserApiController;

@Controller
@RequestMapping("api/updaters")
public class UpdaterApiController extends WithUserApiController<Updater, Long> {
}
