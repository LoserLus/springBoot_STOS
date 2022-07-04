package com.sdust.stos.config;

import com.google.common.base.Predicates;
import com.sdust.stos.anno.MyAnnotation4Swagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SwaggerConfig {

    /**
     * 创建Docket类型的对象，并使用spring容器管理
     * Docket是Swagger中的全局配置对象
     * @return
     */
    @Bean
    public Docket docket(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        //API帮助文档的描述信息
        ApiInfo apiInfo =
                new ApiInfoBuilder()
                        .contact(
                                new Contact(
                                        "JK208_SU",
                                        "http://JK208.com",
                                        "JK208@qq.com")
                        )
                        .title("springBoot_STOS项目开发文档")
                        .description("该项目是用于开发学校教材订购系统")
                        .version("1.0")
                        .build();

        //给docket上下文配置api描述信息
        docket.apiInfo(apiInfo);

        docket = docket
                .select()
                .apis(
                        Predicates.and(
                                Predicates.not(
                                        RequestHandlerSelectors.withMethodAnnotation(
                                                MyAnnotation4Swagger.class)
                                ),
                                RequestHandlerSelectors.basePackage("com.sdust.stos.controller")
                        )
                ).build();


        return docket;
    }


}
