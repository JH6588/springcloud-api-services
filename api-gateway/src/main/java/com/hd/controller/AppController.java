package com.hd.controller;


import com.hd.common.App;
import com.hd.repository.AppRepository;
import com.hd.common.Result;
import com.hd.utils.TokenProccessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
public class AppController {
    @Value("#{'${app.name.list}'.split(',')}")
    private List<String> appNameList;
    @Resource
    private AppRepository appRepository;





    @RequestMapping(method = RequestMethod.POST, value = "/app")
    public Result addApp(@RequestBody App app) {
        System.out.println(app);
        app.setAppKey(TokenProccessor.getInstance().getUniKey());
        if (appNameList.indexOf(app.getAppName()) == -1) {
            return new Result(400, "invalid appName", null);
        }
        try {
            app.setCreateTimeStamp(new Date().getTime());
            appRepository.save(app);
            return new Result(200, "success", null);
        } catch (Exception e) {
            return new Result(403, e.getMessage(), null);
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/app")
    public Result getAllApp() {
        return new Result(200, "success", appRepository.findAll());

    }

    @RequestMapping(method = RequestMethod.POST,value = "/app/change")
    public Result changeSecret(@RequestBody App newApp){
        App app =  appRepository.findByAppKey(newApp.getAppKey());
        if(app !=null){
            app.setSecret(newApp.getSecret());
            app.setLastChangeTimeStamp( new Date().getTime());
            return new Result(200,"success",null);
        }

        return new Result(400,"invalid appKey" ,null);
    }


}
