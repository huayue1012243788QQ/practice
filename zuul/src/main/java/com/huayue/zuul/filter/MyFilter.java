package com.huayue.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.huayue.common.exception.TokenNullException;
import com.huayue.common.global.Result;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/1/10.
 */
@Component
public class MyFilter extends ZuulFilter {
    private static String LOGIN_URI = "/api/user/user/login";
    private static String REGISTER_URI = "/api/user/user/";
    private static String ROLE_URI = "/api/user/role/user-role";
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        // 获取request
        HttpServletRequest request = context.getRequest();
        String uri = request.getRequestURI();
        String method = request.getMethod();
        if (uri.equals(LOGIN_URI) || (uri.equals(REGISTER_URI) && method.equals("POST")) || uri.endsWith("/v2/api-docs") || uri.equals(ROLE_URI)) {
            return null;
        }
        if (method.equals("OPTIONS")) {
            return null;
        }
        // 获取token
        String token = request.getHeader("Authorization");
        if (token == null) {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            context.getResponse().setContentType("application/json;charset=UTF-8");
            TokenNullException e = new TokenNullException();
            String response = JSON.toJSONString(Result.failure(e.getRetCd(),e.getMsgDes()));
            context.setResponseBody(response);
        } else {
            context.setSendZuulResponse(true);
            context.setResponseStatusCode(200);
        }
        return null;
    }
}
