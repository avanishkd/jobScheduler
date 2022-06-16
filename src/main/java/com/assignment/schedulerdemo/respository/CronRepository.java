package com.assignment.schedulerdemo.respository;

import com.assignment.schedulerdemo.entity.Cron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CronRepository extends JpaRepository<Cron, Long> {
}

