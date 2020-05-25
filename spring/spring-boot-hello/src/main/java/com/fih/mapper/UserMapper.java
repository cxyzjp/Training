package com.fih.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.fih.pojo.User;

@Mapper
public interface UserMapper {
	// #{name}:参数占位符
	@Select("select * from User where name = #{name}")
	public List<User> likeName(String name);

	@Select("select * from User where id = #{id}")
	public User getById(long id);

	@Select("select name from User where id = #{id}")
	public String getNameById(long id);

	/**
	 * 保存数据
	 */
	@Insert("insert into User(name) values(#{name})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public void save(User demo);
}
