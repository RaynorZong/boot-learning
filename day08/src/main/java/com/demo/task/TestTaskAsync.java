package com.demo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
@Slf4j
    public class TestTaskAsync {



    @Async
    public  void test(){
        log.info("xxx");
        log.info(Thread.currentThread().getName());
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Async
    public Future<String> get(){
        log.info(Thread.currentThread().getName());
        try{
            Thread.sleep(10000);
            return new AsyncResult<String>("hello");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
