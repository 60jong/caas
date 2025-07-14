package dev._60jong.caas.socketbroker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SocketBrokerApplication {

    public static void main(String[] args) {
        SpringApplication springApp = new SpringApplication(SocketBrokerApplication.class);

        // TCP 소켓 서버 사용으로 Web은 사용하지 않는다.
        springApp.setWebApplicationType(WebApplicationType.NONE);
        springApp.run(args);
    }
}
