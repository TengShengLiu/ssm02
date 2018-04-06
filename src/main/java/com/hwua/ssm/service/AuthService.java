package com.hwua.ssm.service;

import com.hwua.ssm.po.Auth;

import java.util.List;

public interface AuthService {
    public String queryById(Integer dbid);

    public int update(Auth auth);

    public int insert(Auth auth);

    public String queryGrant(Integer dbid,Integer roleId);
}
