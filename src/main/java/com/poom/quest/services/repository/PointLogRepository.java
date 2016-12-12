package com.poom.quest.services.repository;

import org.springframework.stereotype.Repository;

import com.poom.quest.services.domain.SampleModel;
import com.poom.quest.services.domain.log.PointLog;

@Repository
public class PointLogRepository extends GenericRepository<PointLog, Long> {

}
