package com.hwua.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwua.ssm.po.Auth;
import com.hwua.ssm.po.User;
import com.hwua.ssm.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toUser")
    public String toUser() {
        return "user";
    }

    @RequestMapping(value = "/queryUser", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUsers(String userName,String password,String realName,String valid,Integer page,Integer rows) {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", StringUtils.isEmpty(userName) ? null : userName);
        map.put("password", StringUtils.isEmpty(password) ? null : password);
        map.put("realName", StringUtils.isEmpty(realName) ? null : realName);
        map.put("valid",valid);
        map.put("start",(page-1)*rows);
        map.put("rows",rows);
        Map<String, Object> map1 = userService.queryUser(map);
        return JSON.toJSONString(map1);
    }


    @RequestMapping(value = "/addUser",produces = "application/json;utf-8")
    @ResponseBody
    public String addUser(User user){
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(password);
        JSONObject jsonObject = new JSONObject();
        int i = userService.addUser(user);
        if (i == 1){
            jsonObject.put("msg",true);
        }else {
            jsonObject.put("msg",false);
        }
        return jsonObject.toJSONString();
    }

    //当一个业务操作包含多个增删改数据操作时，须要事物控制
    @Transactional
    @RequestMapping(value = "/grantRoles",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String grantRoles(Integer userId,Integer[] roleIds){
        System.out.println("userId = [" + userId + "], roleIds = [" + roleIds + "]");
        int i = userService.grantRoles(userId, roleIds);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",i>0);
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/login",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(String userName,String password,HttpSession session){
        //加密以后再判断
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        Map<String, Object> user = userService.login(password, userName);
        JSONObject jsonObject = new JSONObject();
        if (user!=null){
            session.setAttribute("user",user);
            List<Auth> auths = userService.getAuthByUserId((Integer) user.get("dbid"));
            List<Integer> resources = userService.getResource((Integer) user.get("dbid"));
            session.setAttribute("resources",resources);
//            List<Auth> authTrees = userService.getAuthTreeByUserId((Integer) user.get("dbid"));
//            session.setAttribute("authTrees",authTrees);
            session.setAttribute("auths",auths);
            jsonObject.put("msg",true);
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping("/exit")
    public void exit(HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException {
        session.invalidate();
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }

}
