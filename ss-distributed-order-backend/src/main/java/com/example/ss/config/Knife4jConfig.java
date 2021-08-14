package com.example.ss.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.net.InetAddress;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Knife4j Swagger 配置
 *
 * @author Aaric, created on 2021-08-13T11:35.
 * @version 0.6.0-SNAPSHOT
 * @see com.github.xiaoymin.knife4j.spring.configuration.Knife4jAutoConfiguration
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
                .globalOperationParameters(operationParameters())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.ss"))
                .paths(PathSelectors.any())
                .build();
    }

    @Value("${knife4j.document.title}")
    private String documentTitle;

    @Value("${knife4j.document.description}")
    private String documentDescription;

    @Value("${knife4j.document.version}")
    private String documentVersion;

    @Value("${knife4j.document.developer.name}")
    private String developerName;

    @Value("${knife4j.document.developer.url}")
    private String developerUrl;

    @Value("${knife4j.document.developer.email}")
    private String developerEmail;

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(documentTitle)
                .description(documentDescription)
                .termsOfServiceUrl(MessageFormat.format("http://{0}:{1}/doc.html", serverHost, serverPort))
                .contact(new Contact(developerName, developerUrl, developerEmail))
                .version(documentVersion)
                .build();
    }

    private List<Parameter> operationParameters() {
        // Header 请求参数
        List<Parameter> params = new ArrayList<>();
        params.add(getParameterBuilder("locale", "国际化参数", "query", "zh_CN", false).build());
        params.add(getParameterBuilder("Authorization", "OAuth2 Token", "header", "Bearer token", false).build());
        return params;
    }

    private ParameterBuilder getParameterBuilder(String name, String description, String parameterType, String example, boolean required) {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name(name).description(description)
                .modelRef(new ModelRef("string"))
                .parameterType(parameterType)
                .scalarExample(example)
                .required(required);
        return parameterBuilder;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        serverHost = InetAddress.getLocalHost().getHostAddress();
    }
}
