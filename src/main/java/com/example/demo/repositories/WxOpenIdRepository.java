package com.example.demo.repositories;

import com.example.demo.domain.model.User;
import com.example.demo.domain.model.WxOpenId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WxOpenIdRepository extends JpaRepository<WxOpenId,String> {
    WxOpenId findByUser(User user);

    WxOpenId findByOpenId(String openId);
}
