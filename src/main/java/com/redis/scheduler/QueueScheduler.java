package com.redis.scheduler;

import com.redis.entity.EmployeeEntity;
import com.redis.repo.EmployeeRepository;
import com.redis.service.RedisQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "redis.scheduler.enabled", matchIfMissing = true)
public class QueueScheduler {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RedisQueueService redisQueueService;

    @Scheduled(cron = "${redis.scheduler.cron}",zone="Asia/Kolkata")
    public void schedule() {
      while(redisQueueService.queueSize()>0)
      {
          EmployeeEntity employeeEntity= (EmployeeEntity) redisQueueService.popFromQueue();
          employeeRepository.save(employeeEntity);
      }
      redisQueueService.clearQueue();
    }
}

