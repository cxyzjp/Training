package com.cxy.shardingjdbc.repository;

import com.cxy.shardingjdbc.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRepository {

    void createIfNotExistsTable();

    Long insert(User model);

    List<User> selectAll();
}
