package com.assignment.schedulerdemo.respository;

import com.assignment.schedulerdemo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDefinitionRepository extends JpaRepository<Task, String> {
}
