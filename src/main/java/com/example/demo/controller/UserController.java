package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.selfEntity.ResultInfo;
import com.example.demo.service.UserService;
import com.example.demo.util.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/getAllUser")
    public ResultInfo getAll(@RequestBody Params params) {
        ResultInfo resultInfo = userService.getAll(params);
        return resultInfo;
    }
    @RequestMapping("/getAll")
    private List<User> getAll(){
        List<User> users=userService.getAll();
        //将查询到的集合遍历一下
        for (User u:users){
            System.out.println("username:"+u.getUsername()+"_______password:"+u.getPassword());
        }
        return users;
    }

}
