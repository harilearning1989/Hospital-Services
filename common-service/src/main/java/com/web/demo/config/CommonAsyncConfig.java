package com.web.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class CommonAsyncConfig implements AsyncConfigurer {

    private Environment environment;

    @Autowired
    public CommonAsyncConfig setEnvironment(Environment environment) {
        this.environment = environment;
        return this;
    }

    @Override
    public Executor getAsyncExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor=
                new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(
                environment.getProperty("threadpool.initial.size",Integer.class,10));
        threadPoolTaskExecutor.setMaxPoolSize(
                environment.getProperty("threadpool.max.size",Integer.class,50));
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

}
