package com.esreven.tickets.scheduling;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.elasticsearch.threadpool.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledListing {

    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Async
    @Scheduled(cron = "*/5 * * * * *")
    public void currentTime() {
        log.info("Current Time = {}", dateFormat.format(new Date()));
    }
}
