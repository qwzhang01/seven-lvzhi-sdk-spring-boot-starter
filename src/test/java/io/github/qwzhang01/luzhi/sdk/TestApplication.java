package io.github.qwzhang01.luzhi.sdk;

import io.github.qwzhang01.luzhi.sdk.autoconfigure.LvzhiDrpProperties;
import io.github.qwzhang01.luzhi.sdk.client.LvzhiDrpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 测试用 Spring Boot 应用程序入口
 */
@SpringBootApplication
public class TestApplication {

    @TestConfiguration
    static class TestConfig {
        @Bean
        public LvzhiDrpClient lvzhiDrpClient(LvzhiDrpProperties lvzhiDrpProperties) {
            CloseableHttpClient lvzhiDrpHttpClient = HttpClientBuilder.create()
                    .build();
            return new LvzhiDrpClient(lvzhiDrpHttpClient, lvzhiDrpProperties);
        }
    }
}