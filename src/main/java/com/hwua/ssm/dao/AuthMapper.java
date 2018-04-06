package com.hwua.ssm.dao;

import com.hwua.ssm.po.Auth;

import java.util.List;
import java.util.Map;

public interface AuthMapper {
    public List<Auth> queryById(Integer dbid);

    public int update(Auth auth);

    public int doInsert(Auth auth);

    public List<Map<String,Object>> queryGrant(Integer dbid);

    public List<Integer> queryValidAuth(Integer roleId);

}
