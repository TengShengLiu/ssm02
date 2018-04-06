package com.hwua.ssm.service;

import com.hwua.ssm.po.Auth;
import com.hwua.ssm.po.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public Map<String,Object> queryUser(Map<String,Object> params);

    public int addUser(User user);

    public int grantRoles(Integer userId, Integer[] roleIds);

    public Map<String,Object> login(String password,String userName);

    public List<Auth> getAuthByUserId(Integer userId);

//    public List<Auth> getAuthTreeByUserId(Integer userId);

    public List<Integer> getResource(Integer userId);
}
