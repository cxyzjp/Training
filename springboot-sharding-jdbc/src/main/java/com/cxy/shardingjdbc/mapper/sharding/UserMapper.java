package com.cxy.shardingjdbc.mapper.sharding;

import com.cxy.shardingjdbc.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void createIfNotExistsTable();

    Long insert(User model);

    List<User> selectAll();
}
