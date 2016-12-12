package com.poom.quest.services.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.domain.IssueComment;

@Service
@Transactional
public class IssueCommentService extends GenericService<IssueComment, Long> {
	
}
