package com.cxy.shardingjdbc;

import com.cxy.shardingjdbc.entity.a.Material;
import com.cxy.shardingjdbc.mapper.a.MaterialMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MaterialTest extends BaseTest {

    @Autowired
    MaterialMapper materialMapper;

    @Test
    public void init(){
        //  sellerSn,  name,  type,  size,  line,  level
        Material r = new Material(9527,"root", 0, 0, 1, 1);
        Material p = new Material(9527,"图片", 0, 0, 2, 2);
        Material a = new Material(9527,"音频", 0, 0, 3, 2);
        Material v = new Material(9527,"视频", 0, 0, 4, 2);
        Material e = new Material(9527,"end", 0, 0, 5, 0);
        materialMapper.insertSelective(r);
        materialMapper.insertSelective(p);
        materialMapper.insertSelective(a);
        materialMapper.insertSelective(v);
        materialMapper.insertSelective(e);
    }

    @Test
    public void insert(){
        // 插入节点
        materialMapper.updateByLine(9527, 8);
        Material p = new Material(9527,"v11.mp4", 1, 211, 8, 4);
        materialMapper.insertSelective(p);
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

    // 同级上下移动
}
