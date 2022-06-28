package com.sdust.stos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication
@ServletComponentScan
public class STOS_Application {
    public static void main(String[] args) {
        SpringApplication.run(STOS_Application.class,args);
        log.info("项目启动成功");
    }
}
