package com.hd.controller;


import com.hd.common.App;
import com.hd.common.Result;
import com.hd.common.Token;
import com.hd.config.RedisService;
import com.hd.repository.AppRepository;
import com.hd.utils.TokenProccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
public class TokenController {

    private static final Logger logger = LoggerFactory.getLogger(TokenController.class);
    @Resource
    private AppRepository appRepository;

    @Resource
    private RedisService redisService;
    @Value("${api.max.access}")
    private Integer maxAccessTime;
    @Value("${token.expire.times}")
    private Integer tokenExpireTime;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public Result getTokenWithExpireTime(@RequestParam("appkey") String appKey, @RequestParam("secret") String secret,
                                         @RequestParam("expired") Boolean expired) {

        App app = appRepository.findByAppKey(appKey);
        if (app == null) {

            return new Result(404, "invalid appKey", null);
        }
        if (!secret.equals(app.getSecret())) {
            return new Result(404, "invalid secret ,", null);

        }

        if (expired) {
            Token oldToken = (Token) redisService.getToken(appKey);
            if (oldToken != null) {

                return new Result(200, "success", oldToken.getAccessToken());
            }
            String tokenStr = TokenProccessor.getInstance().getToken();
            Token token = new Token(app.getAppName(), appKey, tokenStr, maxAccessTime, new Date().getTime(), true);
            redisService.putTokenWithExpireTime(appKey, token, tokenExpireTime, TimeUnit.SECONDS);
            return new Result(200, "success", token);
        }
        String tokenStr = TokenProccessor.getInstance().getToken();
        Token token = new Token(app.getAppName(), appKey, tokenStr, maxAccessTime, new Date().getTime(), false);

        redisService.putToken(appKey, token);
        return new Result(200, "success", token);
    }


    @RequestMapping(value = "token/verify", method = RequestMethod.POST)
    public Result verifyToken(@RequestParam("token") String tokenStr, @RequestParam("appkey") String appKey) {
        logger.info("TOKEN  == " + tokenStr);
        Token token = (Token) redisService.getToken(appKey);


        if (token != null && tokenStr.equals(token.getAccessToken())) {
            String appSpeedKey = appKey + "---SPEED";
            if (redisService.getKey(appSpeedKey) == null) {
                redisService.generate(appSpeedKey, 1, 60, TimeUnit.SECONDS);

            } else {
                Integer accessTime = (Integer) redisService.getKey(appSpeedKey);
                if (accessTime > token.getMaxAccessTime()) {
                    return new Result(405, "please wait", null);
                }
            }

            logger.info("SUCESS");
            return new Result(200, "success", null);
        }
        return new Result(403, "invalid token ", null);
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(value = {Exception.class,})
    public Result handleException(Exception ex) {


        logger.error(ex.getMessage());
        ex.printStackTrace();
        return new Result(500, "innternal error", null);
    }


}
