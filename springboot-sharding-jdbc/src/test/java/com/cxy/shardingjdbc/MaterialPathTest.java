package com.cxy.shardingjdbc;

import com.cxy.shardingjdbc.entity.a.Material;
import com.cxy.shardingjdbc.mapper.a.MaterialMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MaterialPathTest extends BaseTest {

    @Autowired
    MaterialMapper materialMapper;

    @Test
    public void init(){

    }

    @Test
    public void insert(){
        Material material = null;
        for (int i=20000;i<20010;i++){
            material = new Material();
            material.setSellerSn(i);
            material.setPath("1001_" + i);
            materialMapper.insertSelective(material);
        }
        for (int i=10000;i<99999;i++){
            material = new Material();
            material.setSellerSn(i);
            material.setPath("1002_" + i);
            materialMapper.insertSelective(material);
        }
    }

    @Test
    public void delete(){
        // 删除节点
        materialMapper.delete(9527, 8);
    }

    @Test
    public void getAllChildren(){
        // 查询所有子孙节点
        System.out.println(materialMapper.selectAllChildren(9527, 6, 2));
    }

    @Test
    public void getChildren(){
        // 查询下一级所有子节点
        System.out.println(materialMapper.selectChildren(9527, 6, 2));
    }
}
