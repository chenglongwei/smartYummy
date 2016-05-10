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

    @Scheduled(fixedRate = 5000)
    public void chief1() {
        System.out.println("worker1, The time is now " + dateFormat.format(new Date()));
    }

    @Scheduled(fixedRate = 5000)
    public void chief2() {
        System.out.println("worker2, The time is now " + dateFormat.format(new Date()));
    }

    @Scheduled(fixedRate = 5000)
    public void chief3() {
        System.out.println("worker3, The time is now " + dateFormat.format(new Date()));
    }


}
