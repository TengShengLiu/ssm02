package com.hwua.ssm.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.hwua.ssm.dao.AuthMapper;
import com.hwua.ssm.po.Auth;
import com.hwua.ssm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("authService")
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public String queryById(Integer dbid) {
        List<Auth> auths = authMapper.queryById(-1);
        return JSON.toJSONString(auths);
    }

    @Override
    public int update(Auth auth) {
        return authMapper.update(auth);
    }

    @Override
    public int insert(Auth auth) {
        return authMapper.doInsert(auth);
    }

    @Override
    public String queryGrant(Integer dbid,Integer roleId) {
        List<Map<String,Object>> validAuth = authMapper.queryGrant(dbid);
        List<Integer> auths = authMapper.queryValidAuth(roleId);
        paseAuth(validAuth,auths);
        return JSON.toJSONString(validAuth,true);
    }

    private void paseAuth(List<Map<String,Object>> validAuth,List<Integer> auths){
        for (Map<String,Object> auth:validAuth){
            if (auths.contains(auth.get("id"))){
                auth.put("checked",true);
            }
            List<Map<String, Object>> children = (List<Map<String, Object>>) auth.get("children");
            paseAuth(children,auths);
        }


    }


}
