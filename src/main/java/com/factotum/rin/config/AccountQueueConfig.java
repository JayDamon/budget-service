package com.factotum.rin.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

public class AccountQueueConfig {

    public static final String ACCOUNT_CHANGE = "account_change";

    public static final String ACCOUNT_ADDED = "account_added";

    @Bean
    Queue accountChangedQueue() {
        return new Queue(ACCOUNT_CHANGE, false);
    }

    @Bean
    Queue accountAddedQueue() {
        return new Queue(ACCOUNT_ADDED, false);
    }

}
