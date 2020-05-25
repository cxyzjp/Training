package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * description:
 * author: bowen
 * date: 2019/3/12
 */
@Data
@Builder
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String sex;
    private Date createTime;
}
