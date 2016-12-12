package com.poom.quest.web.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poom.quest.services.domain.Updater;
import com.poom.quest.web.controller.api.generic.WithUserApiController;

@RestController
@RequestMapping("api/updaters")
public class UpdaterApiController extends WithUserApiController<Updater, Long> {
}
