package com.wiredelta.backend.repository;

import com.wiredelta.backend.entity.AccessToken;
import com.wiredelta.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<AccessToken,Long> {

    List<AccessToken> findByUser(User user);

}
