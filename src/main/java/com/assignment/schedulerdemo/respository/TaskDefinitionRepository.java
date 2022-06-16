package com.assignment.schedulerdemo.respository;

import com.assignment.schedulerdemo.entity.TaskDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDefinitionRepository extends JpaRepository<TaskDefinition, String> {
}
