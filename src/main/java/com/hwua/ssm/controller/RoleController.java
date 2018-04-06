package com.hwua.ssm.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwua.ssm.po.Role;
import com.hwua.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/toRole")
    public String toRole(){
        return "role";
    }

    @RequestMapping(value = "/queryRole",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getRole(String roleName,String roleCode,String valid,Integer page,Integer rows){
        Map<String, Object> map = new HashMap<String, Object>();
        /*StringUtils.isEmpty(roleName) ? null : roleName
        操作String的工具类，判断是否为空字符串
         */
        map.put("roleName", StringUtils.isEmpty(roleName) ? null : roleName);
        map.put("roleCode",StringUtils.isEmpty(roleCode) ? null : roleCode);
        map.put("valid",valid);
        map.put("start",(page - 1) * rows);
        map.put("rows",rows);
        Map<String, Object> map1 = roleService.queryRole(map);
        return JSON.toJSONString(map1);
    }

    @RequestMapping(value = "/addRole",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addRole(Role role){
        System.out.println("role = [" + role + "]");
        int i = roleService.addRole(role);
        JSONObject jsonObject = new JSONObject();
        if (i==1){
            jsonObject.put("msg",true);
        }else {
            jsonObject.put("msg",false);
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/grantAuths",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String grantAuths(Integer roleId,Integer[] authIds){
        System.out.println("roleId = [" + roleId + "], auths = [" + Arrays.toString(authIds) + "]");
        int i = roleService.grantAuths(roleId, authIds);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",i>0);
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/getValidRole",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUserCheckedRole(Integer userId){
        return roleService.getUserCheckedRole(userId);
    }

}
