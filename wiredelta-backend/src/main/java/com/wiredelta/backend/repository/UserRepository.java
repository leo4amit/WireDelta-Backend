package com.wiredelta.backend.repository;

import com.wiredelta.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserName(String userName);
}
