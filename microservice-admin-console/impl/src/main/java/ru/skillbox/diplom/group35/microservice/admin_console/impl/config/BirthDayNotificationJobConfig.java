package ru.skillbox.diplom.group35.microservice.admin_console.impl.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BirthDayNotificationJobConfig {
    private final BirthDayInfoConfig birthDayInfoConfig;

    @Bean
    JobDetail initBirthDayJob() {
        Class<? extends QuartzJobBean> jobClass = getClass(birthDayInfoConfig.getClassName());
        return JobBuilder
                .newJob(jobClass)
                .withIdentity(birthDayInfoConfig.getName())
                .storeDurably()
                .requestRecovery(true)
                .build();
    }

    @Bean
    Trigger initBirthDayTrigger() {
        return TriggerBuilder
                .newTrigger()
                .forJob(birthDayInfoConfig.getName())
                .withIdentity(birthDayInfoConfig.getName())
                .withSchedule(CronScheduleBuilder.cronSchedule(birthDayInfoConfig.getCron()))
                .build();
    }

    private Class<? extends QuartzJobBean> getClass(String ClassName) {
        Class<? extends QuartzJobBean> classObject = null;
        try {
            classObject = (Class<? extends QuartzJobBean>)
                    Class.forName(ClassName);
        } catch (ClassNotFoundException e) {
            log.error("createJob(): ClassNotFoundException on job class {} - {}",
                    ClassName, e.getMessage());
        } catch (Exception e) {
            log.error("createJob(): Exception on job class {} - {}",
                    ClassName, e.getMessage());
        }
        return classObject;
    }
}
