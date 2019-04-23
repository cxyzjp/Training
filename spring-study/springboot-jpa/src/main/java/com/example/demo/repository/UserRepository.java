package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * description:
 * author: bowen
 * date: 2019/3/12
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Slice<User> findByAge(Integer age, Pageable pageable);

    Page<User> findByNameLike(String name, Pageable pageable);
}
