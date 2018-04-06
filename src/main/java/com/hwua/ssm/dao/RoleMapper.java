package com.hwua.ssm.dao;

import com.hwua.ssm.po.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    /**
     * 多条件分页查询
     * @param params
     * @return
     */
    public List<Role> query(Map<String,Object> params);


    /**
     * 多条件查询总共多少条记录
     * @param params
     * @return
     */
    public Integer getCount (Map<String,Object> params);

    /**
     * 添加用户
     * @param role
     * @return
     */
    public int addRole(Role role);

    public int delAuths(Integer roleId);

    public int addAuths(List<Map<String,Integer>> param);

    public List<Map<String,Object>> queryValidRole();

    public List<Integer> queryRoleByUserId(Integer userId);

}
