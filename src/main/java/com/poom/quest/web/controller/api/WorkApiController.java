
package com.poom.quest.web.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poom.quest.services.domain.Work;
import com.poom.quest.web.controller.api.generic.TreeApiController;

@RestController
@RequestMapping("api/works")
public class WorkApiController extends TreeApiController<Work, Long> {
}
