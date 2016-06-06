package com.poom.quest.services.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.model.Issue;
import com.poom.quest.services.model.SampleModel;

@Service
@Transactional
public class IssueService extends GenericService<Issue> {
	
}
