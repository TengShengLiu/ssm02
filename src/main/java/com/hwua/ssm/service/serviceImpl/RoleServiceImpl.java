package com.hwua.ssm.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.hwua.ssm.dao.RoleMapper;
import com.hwua.ssm.po.Role;
import com.hwua.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Map<String,Object> queryRole(Map<String, Object> params) {
        Map<String, Object> map = new HashMap<>();
        List<Role> roleMap = roleMapper.query(params);
        int count = roleMapper.getCount(params);
        map.put("total",count);
        map.put("rows",roleMap);
        return map;
    }

    @Override
    public int addRole(Role role) {
        return roleMapper.addRole(role);
    }

    @Override
    public int grantAuths(Integer roleId, Integer[] authIds) {
        int i = roleMapper.delAuths(roleId);
        List<Map<String, Integer>> list = new ArrayList<>();
        for (Integer authId: authIds) {
            Map<String, Integer> map = new HashMap<>();
            map.put("roleId",roleId);
            map.put("authId",authId);
            list.add(map);
        }
        i+=roleMapper.addAuths(list);
        return i;
    }

    @Override
    public String getUserCheckedRole(Integer userId) {
        List<Map<String, Object>> maps = roleMapper.queryValidRole();
        List<Integer> userIds = roleMapper.queryRoleByUserId(userId);
        for (Map<String,Object> map:maps) {
            if (userIds.contains(map.get("dbid"))){
                map.put("checked",true);
            }
        }
        return JSON.toJSONString(maps);
    }
}
