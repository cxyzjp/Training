package com.example.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * description:
 * author: bowen
 * date: 2019/7/19
 */
@RestController
public class HiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HiController.class);

    @Value("${server.port}")
    private String port;

    /**
     * 不需要任何权限，只要Header中的Token正确即可
     */
    @RequestMapping("/hi")
    public String hi() {
        return "hi : " + ",i am from port: " + port;
    }

    /**
     * 需要ROLE_ADMIN权限
     */
    @PreAuthorize("hasAuthority('A')")
    @RequestMapping("/hello")
    public String hello() {
        return "hello you!";
    }

    /**
     * 获取当前认证用户的信息
     */
    @GetMapping("/getPrinciple")
    public OAuth2Authentication getPrinciple(
            OAuth2Authentication oAuth2Authentication, Principal principal, Authentication authentication) {
        LOGGER.info(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
        LOGGER.info(oAuth2Authentication.toString());
        LOGGER.info("principal.toString()" + principal.toString());
        LOGGER.info("principal.getName()" + principal.getName());
        LOGGER.info("authentication:" + authentication.getAuthorities().toString());

        return oAuth2Authentication;
    }
}

