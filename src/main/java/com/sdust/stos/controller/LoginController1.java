package com.sdust.stos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
//@RequestMapping("/login")
public class LoginController1 {

    @RequestMapping(value = "/userlogin",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public String Login(HttpServletRequest request, String username, String pwd){
        log.info("username: {}",username);
        log.info("pwd: {}",pwd);

        return null;
    }


}
