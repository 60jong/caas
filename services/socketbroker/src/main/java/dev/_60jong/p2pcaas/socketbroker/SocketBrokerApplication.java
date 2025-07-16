package dev._60jong.p2pcaas.socketbroker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocketBrokerApplication {

    public static void main(String[] args) {
        SpringApplication springApp = new SpringApplication(SocketBrokerApplication.class);

        // TCP 소켓 서버 사용으로 Web은 사용하지 않는다.
        springApp.setWebApplicationType(WebApplicationType.NONE);
        springApp.run(args);
    }
}
