package com.example.ss.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.net.InetAddress;
import java.text.MessageFormat;
import java.util.Date;

/**
 * Knife4j Swagger2 配置
 *
 * @author Aaric, created on 2021-07-30T11:35.
 * @version 0.1.0-SNAPSHOT
 */
@EnableKnife4j
@EnableSwagger2WebMvc
@Configuration
public class Knife4jConfig implements InitializingBean {

    @Value("localhost")
    private String serverHost;

    @Value("${server.port}")
    private String serverPort;

    @Bean
    Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(serverHost)
                .apiInfo(apiInfo())
                .directModelSubstitute(Date.class, Long.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.ss"))
                .paths(PathSelectors.any())
                .build();
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("在线API文档")
                .description("Spring Security 框架集成测试")
                .termsOfServiceUrl(MessageFormat.format("http://{0}:{1}/doc.html", serverHost, serverPort))
                .contact(new Contact("Aaric", "", "vipaaric@gmail.com"))
                .version("0.13.0")
                .build();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        serverHost = InetAddress.getLocalHost().getHostAddress();
    }
}
