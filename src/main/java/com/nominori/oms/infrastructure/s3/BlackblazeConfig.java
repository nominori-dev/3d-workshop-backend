package com.nominori.oms.infrastructure.s3;

import com.backblaze.b2.client.B2StorageClient;
import com.backblaze.b2.client.B2StorageClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BlackblazeConfig {

    private final String END_POINT = "https://static.bdv.ink";

    @Value("${blackblaze.app-key-id}")
    private String APP_KEY_ID;

    @Value("${blackblaze.app-key}")
    private String APP_KEY;

    @Value("${spring.application.name")
    private String USER_AGENT;

    @Bean
    public B2StorageClient b2StorageClient(){
        return B2StorageClientFactory.createDefaultFactory()
                .create(APP_KEY_ID, APP_KEY, USER_AGENT);
    }


}
