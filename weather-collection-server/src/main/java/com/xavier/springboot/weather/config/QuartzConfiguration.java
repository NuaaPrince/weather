package com.xavier.springboot.weather.config;

import com.xavier.springboot.weather.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description Quartz Configuration.
 * @author: XavierWang
 * @create: 2019-03-17 23:57
 **/
@Configuration
public class QuartzConfiguration {
    private static final int TIME = 1800; // 更新频率

    //JobDetail 定义一个特定的job
    @Bean
    public JobDetail weatherDataSyncJobDetail() {
        return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("weatherDataSyncJob")
        .storeDurably().build();
    }
    //Trigger 针对特定的job定义一个触发器
    @Bean
    public Trigger weatherDataSyncTrigger() {
        SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(TIME).repeatForever();

        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail())
                .withIdentity("weatherDataSyncTrigger").withSchedule(schedBuilder).build();
    }

}
