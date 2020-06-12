package com.example.demo.mapper.oracle;

import com.example.demo.domain.User;
import com.example.demo.util.Params;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> getAll();
    List<User> getAllUser(Params params);
}
