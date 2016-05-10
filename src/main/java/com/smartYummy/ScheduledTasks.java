package com.smartYummy;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenglongwei on 5/10/16.
 */
@Component
public class ScheduledTasks {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0 0 6 * * ?")
    public void chief1() {
        System.out.println("worker1, start to get order, time " + dateFormat.format(new Date()));

    }

    @Scheduled(cron = "0 0 6 * * ?")
    public void chief2() {
        System.out.println("worker2, start to get order, time " + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 0 6 * * ?")
    public void chief3() {
        System.out.println("worker3, start to get order, time " + dateFormat.format(new Date()));
    }

}
