package com.tutorial.bootwebapp;

import jdk.jfr.SettingDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;


@SpringBootApplication
//@EnableScheduling
//@EnableAsync
public class BootWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootWebAppApplication.class, args);
    }


    //    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    //    @Async
    //    public void sendEmails() throws InterruptedException {
    //        System.out.println("Hello world");
    //        Thread.sleep(5000);
    //        System.out.println("Ending the world");
    //    }
    //
    //    @Scheduled(cron = "*/5 * * * * *")
    //    @Async
    //    public void generateSalesEmail() throws InterruptedException {
    //        System.out.println("start sales report");
    //        Thread.sleep(5000);
    //        System.out.println("end sales report");
    //    }
}
