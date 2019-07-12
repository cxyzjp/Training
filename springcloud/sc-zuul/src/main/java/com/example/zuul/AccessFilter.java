package com.example.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * description:
 * author: bowen
 * date: 2019/7/10
 */
@Component
public class AccessFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

    /**
     * pre：可以在请求被路由之前调用
     * routing：在路由请求时候被调用
     * post：在routing和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 定义filter的顺序，数字越小表示顺序越高，越先执行
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断该过滤器是否要执行
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("{} request to {}", request.getMethod(), request.getRequestURL().toString());

        String token = request.getParameter("token");
        if(token == null) {
            log.warn("access token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("access token is empty");
            return null;
        }
        log.info("access token ok");
        return null;
    }

}
