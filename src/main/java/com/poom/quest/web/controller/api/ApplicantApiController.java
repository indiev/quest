package com.poom.quest.web.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poom.quest.services.model.user.Applicant;
import com.poom.quest.web.controller.api.generic.GenericApiController;


@RestController
@RequestMapping("api/applicants")
public class ApplicantApiController extends GenericApiController<Applicant, Long> {
}
