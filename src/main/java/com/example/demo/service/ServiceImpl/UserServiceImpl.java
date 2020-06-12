package com.example.demo.service.ServiceImpl;

import com.example.demo.domain.User;
import com.example.demo.mapper.oracle.UserMapper;
import com.example.demo.selfEntity.ResultInfo;
import com.example.demo.service.UserService;
import com.example.demo.util.Params;
import com.example.demo.util.ResultUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }
    @Override
    public ResultInfo getAll(Params params) {
        /*开启分页*/
        if(params.getPageNum()!=null&&params.getPageSize()!=null){
            PageHelper.startPage(params.getPageNum(),params.getPageSize(),true);
        }
        List<User> users=userMapper.getAllUser(params);
        /*封装查询的list*/
        ResultInfo resultInfo= ResultUtil.getResultInfoOk(users);
        return resultInfo;
    }
}
