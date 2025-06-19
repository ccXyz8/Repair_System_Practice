package org.example.repairsystembackend.Configuration;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfiguration {

    @Bean
    public MinioClient client(){
        return MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("admin","password")
                .build();
    }

}
