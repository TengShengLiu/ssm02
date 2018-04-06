package com.hwua.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.hwua.ssm.po.Auth;
import com.hwua.ssm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/toMain")
    public String mainToAuth(){
        return "auth";
    }

    @RequestMapping(value = "/getAllAuth",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAllAuth(){
        return authService.queryById(-1);
    }


    @RequestMapping(value = "/queryGrant",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAuthByDbid(Integer dbid,Integer value){
        System.out.println("value = " + value);
        return authService.queryGrant(-1,value);
    }


    @RequestMapping(value = "/addOrUpdate",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String update(Auth auth){
        JSONObject jsonObject = new JSONObject();
        if (auth.getDbid()==null){
            //添加
            int i = authService.insert(auth);
            if (i==1){
                jsonObject.put("msg",true);
            }else{
                jsonObject.put("msg",false);
            }
            return jsonObject.toJSONString();
        }else{
            int i = authService.update(auth);
            if (i==1){
                jsonObject.put("msg",true);
            }else{
                jsonObject.put("msg",false);
            }
            return jsonObject.toJSONString();
        }
    }
}
