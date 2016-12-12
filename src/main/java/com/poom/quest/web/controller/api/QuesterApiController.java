package com.poom.quest.web.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poom.quest.services.domain.user.Quester;
import com.poom.quest.web.controller.api.generic.WithUserApiController;


@RestController
@RequestMapping("api/questers")
public class QuesterApiController extends WithUserApiController<Quester, Long> {
}
