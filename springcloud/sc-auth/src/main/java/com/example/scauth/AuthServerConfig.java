package com.example.scauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * description:
 * author: bowen
 * date: 2019/7/19
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    // 将Token存储在内存中
    private TokenStore tokenStore = new InMemoryTokenStore();
//    private TokenStore tokenStore = new JdbcTokenStore(dataSource);

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    @Autowired
    public AuthServerConfig(@Qualifier("authenticationManagerBean") AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 将客户端的信息存储在内存中
        clients.inMemory()
                // 创建了一个client名为browser的客户端
                .withClient("browser")
                // 配置验证类型
                .authorizedGrantTypes("refresh_token", "password")
                // 配置客户端域为“ui”
                .scopes("ui")
                .and()
                .withClient("sc-consumer")
                .secret("123456")
                .authorizedGrantTypes("client_credentials", "refresh_token", "password")
                .scopes("server");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 配置Token的存储方式
        endpoints.tokenStore(tokenStore)
                // 注入WebSecurityConfig配置的bean
                .authenticationManager(authenticationManager)
                // 读取用户的验证信息
                .userDetailsService(userService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        // 对获取Token的请求不再拦截
        oauthServer.tokenKeyAccess("permitAll()")
                // 验证获取Token的验证信息
                .checkTokenAccess("isAuthenticated()");
    }
}
