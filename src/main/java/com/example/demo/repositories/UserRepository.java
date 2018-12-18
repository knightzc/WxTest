package com.example.demo.repositories;

import com.example.demo.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByUserName(String userName);

    User findByUserNo(String userNo);
}
