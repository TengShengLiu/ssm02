package com.hwua.ssm.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.hwua.ssm.dao.UserMapper;
import com.hwua.ssm.po.Auth;
import com.hwua.ssm.po.User;
import com.hwua.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String,Object> queryUser(Map<String, Object> params) {
        //准备数据，数据类型为Map类型的，包括总共多少条和具体数据List
        List<User> users = userMapper.queryUser(params);
        int count = userMapper.getCount(params);
        Map<String, Object> map = new HashMap<>();
        map.put("total",count);
        map.put("rows",users);
        return map;
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int grantRoles(Integer userId, Integer[] roleIds) {
        int i = userMapper.delRoles(userId);
        List<Map<String, Integer>> maps = new ArrayList<>();
        for (Integer roleId:roleIds) {
            Map<String, Integer> map = new HashMap<>();
            map.put("userId",userId);
            map.put("roleId",roleId);
            maps.add(map);
        }
        i =+ userMapper.addRoles(maps);
        return i;
    }

    @Override
    public Map<String,Object> login(String password, String userName) {
        Map<String, Object> user = new HashMap<>();
        user.put("password",password);
        user.put("userName",userName);
        return userMapper.login(user);
    }

    public List<Auth> getAuthByUserId(Integer userId){
        List<Auth> auths = userMapper.queryAuthsByUserId(userId);
        Auth father =null,son = null;
        List<Auth> children = null;

        for(int i = auths.size()-1;i >= 0;i--){
            //默认层级最低层作为父类，往层级高的遍历，一旦二层循环子类的父类id能对上一层循环遍历的id
            //就找到了一层循环的子类了，最后别忘了把找到的子类添加给父类还需注意算法的漏洞
            father = auths.get(i);
            children = new ArrayList<>();
            for (int j = auths.size()-1;j>=0;j--){
                son = auths.get(j);
                if(father.getDbid().equals(son.getParentId())){
                    children.add(son);
                    auths.remove(son);
                }
            }
            father.setChildren(children);
        }
        //检查数据，删除掉layer层级不为1 的数据
        for(int i = 0;i<auths.size()-1;i++){
            Auth auth = auths.get(i);
            if(auth.getLayer() != 1){
                auths.remove(auth);
                i--;
            }
        }
        return auths;
    }

    @Override
    public List<Integer> getResource(Integer userId) {
        return userMapper.queryResouce(userId);
    }

   /* public List<Auth> getAuthTreeByUserId(Integer userId){
        List<Auth> auths = userMapper.queryAuthsByUserId(userId);
        Auth father =null,son = null;
        List<Auth> children = null;
        for(int i = auths.size()-1;i >= 0;i--){
            //默认层级最低层作为父类，往层级高的遍历，一旦二层循环子类的父类id能对上一层循环遍历的id
            //就找到了一层循环的子类了，最后别忘了把找到的子类添加给父类还需注意算法的漏洞
            father = auths.get(i);
            children = new ArrayList<>();
            for (int j = auths.size()-1;j>=0;j--){
                son = auths.get(j);
                if(father.getDbid().equals(son.getParentId())){
                    children.add(son);
                    auths.remove(son);
                }
            }
            father.setChildren(children);
        }
        //检查数据，删除掉layer层级不为1 的数据
        for(int i = 0;i<auths.size()-1;i++){
            Auth auth = auths.get(i);
            if(auth.getLayer() == 1){
                auths.remove(auth);
                i--;
            }
        }
        return auths;
    }*/
}
