package com.hd.config;

import com.hd.common.Result;
import com.hd.common.Token;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Component
public class ApiFilter extends ZuulFilter {

    @Value("${api.filter.path.prefix}")
    private String apiPathPrefix;
    @Resource
    private RedisService redisService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext cx = RequestContext.getCurrentContext();
        HttpServletRequest request = cx.getRequest();
        System.out.println("ZUUL FILTER  " + request.getRequestURI());
        return request.getRequestURI().startsWith(apiPathPrefix);
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        ctx.setSendZuulResponse(false);
        String tokenStr = request.getParameter("token");
        String appKey = request.getParameter("appkey");
        if ( StringUtils.isEmpty(tokenStr) | StringUtils.isEmpty(appKey) || redisService.getToken(appKey) == null){
            ctx.setResponseStatusCode(HttpStatus.BAD_REQUEST.value());
            ctx.setResponseBody(new Result(HttpStatus.BAD_REQUEST.value(), "invalid token ", null).toString());
            return  null;
        }
        Token token = (Token) redisService.getToken(appKey);
        if (! tokenStr.equals(token.getAccessToken())) {

            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            ctx.setResponseBody(new Result(HttpStatus.UNAUTHORIZED.value() ,"invalid token ", null).toString());
            return null;
        }
            String appSpeedKey = appKey + "---SPEED";
            if (redisService.getKey(appSpeedKey) == null) {
                redisService.generate(appSpeedKey, 1, 60, TimeUnit.SECONDS);

            } else {
                Integer accessTime = (Integer) redisService.getKey(appSpeedKey);
                if (accessTime > token.getMaxAccessTime()) {
                    ctx.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
                    ctx.setResponseBody(new Result(HttpStatus.TOO_MANY_REQUESTS.value(), "please wait", null).toString());
                }
            }
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);

            return  null;

    }


}
