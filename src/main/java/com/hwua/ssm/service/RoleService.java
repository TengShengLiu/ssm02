package com.hwua.ssm.service;

import com.hwua.ssm.po.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface RoleService {
    public Map<String,Object> queryRole(Map<String,Object> params);

    public int addRole(Role role);

    public int grantAuths(Integer roleId,Integer[] authIds);

    public String getUserCheckedRole(Integer userId);
}
