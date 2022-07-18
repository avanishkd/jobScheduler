package com.assignment.schedulerdemo.respository;

import com.assignment.schedulerdemo.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobDefinitionRepository extends JpaRepository<JobEntity, String> {

    JobEntity findByJobId(Long jobId);
}
