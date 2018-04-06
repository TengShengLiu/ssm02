package com.hwua.ssm.dao;

import com.hwua.ssm.po.Auth;
import com.hwua.ssm.po.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    /**
     * 多条件查询
     * @param params 多条件
     * @return
     */

    public List<User> queryUser(Map<String,Object> params);

    public int getCount(Map<String,Object> params);

    public int addUser(User user);

    public int delRoles(Integer userId);

    public int addRoles(List<Map<String,Integer>> param);

    public Map<String,Object> login(Map<String,Object> user);

    public List<Auth> queryAuthsByUserId(Integer UserId);

    public List<Integer> queryResouce(Integer userId);
}
