package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.selfEntity.ResultInfo;
import com.example.demo.util.Params;

import java.util.List;

public interface UserService {
    List<User> getAll();
    ResultInfo getAll(Params params);
}
