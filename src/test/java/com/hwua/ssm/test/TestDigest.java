package com.hwua.ssm.test;

import com.alibaba.fastjson.JSON;
import com.hwua.ssm.controller.RoleController;
import com.hwua.ssm.dao.AuthMapper;
import com.hwua.ssm.dao.RoleMapper;
import com.hwua.ssm.dao.UserMapper;
import com.hwua.ssm.po.Auth;
import com.hwua.ssm.po.Role;
import com.hwua.ssm.po.User;
import com.hwua.ssm.service.UserService;
import com.hwua.ssm.service.serviceImpl.AuthServiceImpl;
import com.hwua.ssm.service.serviceImpl.RoleServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDigest {
    @Test
    public void testDigest(){
        String str = "123456";
        String s = DigestUtils.md5DigestAsHex(str.getBytes());
        System.out.println("s = " + s);
    }

    @Test
    public void testQueryById(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        AuthMapper authMapper = ctx.getBean(AuthMapper.class);
        List<Auth> auths = authMapper.queryById(-1);

        System.out.println(JSON.toJSONString(auths,true));
    }

    @Test
    public void testQury(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        RoleMapper roleMapper = ctx.getBean(RoleMapper.class);
        Map<String, Object> map = new HashMap<>();
//        map.put("roleName","管理员");
        map.put("start",(2-1)*2);
        map.put("rows",2);
        List<Role> query = roleMapper.query(map);
        Integer count = roleMapper.getCount(map);
        System.out.println("count = " + count);
        System.out.println("query = " + query);
    }

    @Test
    public void testQueryGrant(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        AuthMapper authMapper = ctx.getBean(AuthMapper.class);
        AuthServiceImpl bean = ctx.getBean(AuthServiceImpl.class);
        String s = bean.queryGrant(-1, 11);
        System.out.println("s = " + s);

        /*List<Integer> maps = authMapper.queryValidAuth(11);
        System.out.println("maps = " + maps);*/


        /*List<Map<String,Object>> auths = authMapper.queryGrant(-1);
        String string = JSON.toJSONString(auths, true);
        System.out.println("string = " + string);*/
    }


    @Test
    public void testUser(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
//        RoleMapper bean = ctx.getBean(RoleMapper.class);
       /* List<Map<String, Object>> maps = bean.queryValidRole();
        System.out.println("maps = " + maps);*/

//        List<Integer> integers = bean.queryRoleByUserId(26);
//        System.out.println("integers = " + integers);

       /* RoleServiceImpl bean = ctx.getBean(RoleServiceImpl.class);
        String validRole = bean.getValidRole(1);
        System.out.println("validRole = " + validRole);*/



        UserMapper bean = ctx.getBean(UserMapper.class);
        Map<String, Object> map = new HashMap<>();
        String password = "123456";
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        map.put("userName","admin");
        map.put("password",password);
        Map<String, Object> login = bean.login(map);
        System.out.println("login = " + login);
    }

    @Test
    public void testGetAuthsByuserId() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        UserMapper bean = ctx.getBean(UserMapper.class);
        List<Integer> integers = bean.queryResouce(35);
        System.out.println("integers = " + integers);
    }

}

