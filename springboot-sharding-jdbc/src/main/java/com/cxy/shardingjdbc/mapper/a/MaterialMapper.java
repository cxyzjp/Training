package com.cxy.shardingjdbc.mapper.a;

import com.cxy.shardingjdbc.entity.a.Material;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MaterialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Material record);

    int insertSelective(Material record);

    Material selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);

    @Update("update material set line = line + 1 where seller_sn = #{sellerSn} and line >= #{line}")
    int updateByLine(@Param("sellerSn") int sellerSn, @Param("line") int line);

    @Delete("delete from material where seller_sn = #{sellerSn} and line >= #{line} and line < " +
            " (select min(line) from material where seller_sn = #{sellerSn} and line > #{line} and level <= #{level}) ")
    int delete(@Param("sellerSn") int sellerSn, @Param("line") int line);

    @Select("select * from material where seller_sn = #{sellerSn} and line > #{line} and line < " +
            " (select min(line) from material where seller_sn = #{sellerSn} and line > #{line} and level <= #{level}) " +
            " order by line ")
    List<Material> selectAllChildren(@Param("sellerSn") int sellerSn, @Param("line") int line, @Param("level") int level);

    @Select("select * from material where seller_sn = #{sellerSn} and level =  #{level} + 1 and line > #{line} and line < " +
            " (select min(line) from material where seller_sn = #{sellerSn} and line > #{line} and level <= #{level}) " +
            " order by name ")
    List<Material> selectChildren(@Param("sellerSn") int sellerSn, @Param("line") int line, @Param("level") int level);

}