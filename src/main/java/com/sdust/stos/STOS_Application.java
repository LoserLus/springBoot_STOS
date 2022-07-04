package com.sdust.stos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @EnableSwagger2 是springfox提供的一个注解，代表swagger2相关技术开启
 * 会扫描当前类所在包及其子包中所有的类型中的注解，做swagger文档的定制
 */

@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@EnableSwagger2
public class STOS_Application {
    public static void main(String[] args) {
        SpringApplication.run(STOS_Application.class,args);
        log.info("项目启动成功");
    }
}
