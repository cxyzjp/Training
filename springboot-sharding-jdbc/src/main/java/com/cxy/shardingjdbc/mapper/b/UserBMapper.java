package com.cxy.shardingjdbc.mapper.b;

import com.cxy.shardingjdbc.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserBMapper {

    void createIfNotExistsTable();

    Long insert(User model);

    List<User> selectAll();
}
