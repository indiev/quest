package com.poom.quest.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.user.Applicant;
import com.poom.quest.web.controller.api.generic.GenericApiController;


@Controller
@RequestMapping("api/applicants")
public class ApplicantApiController extends GenericApiController<Applicant, Long> {
}
