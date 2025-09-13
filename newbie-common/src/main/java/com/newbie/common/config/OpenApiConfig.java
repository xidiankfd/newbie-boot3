package com.newbie.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * OpenAPI 配置类
 * 
 * @author Claude
 * @date 2024-09-09
 */
@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "Bearer Authentication";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("NewbieBoot3 API 文档")
                        .description("基于 Spring Boot 3 和 Java 21 构建的多模块企业管理系统")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("NewbieBoot3 开发团队")
                                .email("398698424@qq.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("开发环境"),
                        new Server().url("https://api.newbie.com").description("生产环境")
                ))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
                                .name(SECURITY_SCHEME_NAME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .description("请输入 Bearer Token")))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME));
    }

    @Bean
    public GroupedOpenApi systemApi() {
        return GroupedOpenApi.builder()
                .group("系统管理")
                .packagesToScan("com.newbie.controller.system")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    // 系统管理接口需要认证
                    operation.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME));
                    return operation;
                })
                .build();
    }

    @Bean
    public GroupedOpenApi securityApi() {
        return GroupedOpenApi.builder()
                .group("安全控制")
                .packagesToScan("com.newbie.controller.security")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    // 安全控制接口需要认证（除了登录接口）
                    String path = handlerMethod.getBeanType().getAnnotation(org.springframework.web.bind.annotation.RequestMapping.class).value()[0];
                    String methodPath = "";
                    if (handlerMethod.hasMethodAnnotation(org.springframework.web.bind.annotation.PostMapping.class)) {
                        String[] values = handlerMethod.getMethodAnnotation(org.springframework.web.bind.annotation.PostMapping.class).value();
                        if (values.length > 0) {
                            methodPath = values[0];
                        }
                    }
                    
                    // 登录相关接口不需要认证
                    if (!methodPath.contains("login") && !methodPath.contains("captcha")) {
                        operation.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME));
                    }
                    return operation;
                })
                .build();
    }

    @Bean
    public GroupedOpenApi fileApi() {
        return GroupedOpenApi.builder()
                .group("文件服务")
                .packagesToScan("com.newbie.controller.file")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    // 文件服务接口需要认证
                    operation.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME));
                    return operation;
                })
                .build();
    }

    @Bean
    public GroupedOpenApi standardApi() {
        return GroupedOpenApi.builder()
                .group("标准资源管理")
                .packagesToScan("com.newbie.controller.standard")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    // 新增: 标准资源模块接口无需认证，便于测试  2024-09-09
                    // 不添加安全要求，使得这些接口可以直接访问
                    return operation;
                })
                .build();
    }
}