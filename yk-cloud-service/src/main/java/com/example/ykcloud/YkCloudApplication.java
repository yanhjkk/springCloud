package com.example.ykcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.RequestContextFilter;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.net.InetAddress;
import java.net.UnknownHostException;


@EnableSwagger2WebMvc
@SpringBootApplication(scanBasePackages = "com.example")
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableOpenApi
@Slf4j
public class YkCloudApplication {


@Bean
public RequestContextListener requestContextListener(){
    return new RequestContextListener();
}

    @Bean
    public RequestContextFilter requestContextFilter(){
        RequestContextFilter requestContextFilter = new RequestContextFilter();
        requestContextFilter.setThreadContextInheritable(true);
        return requestContextFilter;
    }

    public static void main(String[] args) throws UnknownHostException {
        System.setProperty("nacos.logging.default.config.enabled", "false");
        System.setProperty("spring.devtools.restart.enabled", "true");
        ConfigurableApplicationContext application = SpringApplication.run(YkCloudApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        String applicationName = env.getProperty("spring.application.name");
        log.info("\n----------------------------------------------------------\n\t" +
                "Application " + applicationName + " is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port  + "/\n\t" +
                "External: \thttp://" + ip + ":" + port  + "/\n\t" +
                "Doc: \t\thttp://" + ip + ":" + port + "/doc.html\n" +
                "----------------------------------------------------------");
    }
}
