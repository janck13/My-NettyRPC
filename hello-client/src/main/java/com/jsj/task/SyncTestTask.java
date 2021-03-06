package com.jsj.task;

import com.jsj.service.HelloService;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class SyncTestTask implements Callable<Long> {

    private HelloService helloService;
    private CountDownLatch countDownLatch;

    public SyncTestTask(HelloService helloService, CountDownLatch countDownLatch) {
        this.helloService = helloService;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public Long call() throws Exception {
        countDownLatch.countDown();
        long now = System.currentTimeMillis();
        String res = helloService.hello();
        now = System.currentTimeMillis() - now;
        System.out.println(res + " 耗时：" + now + " ms");
        return now;
    }
}
