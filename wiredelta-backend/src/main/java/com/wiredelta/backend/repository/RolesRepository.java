package com.wiredelta.backend.repository;

import com.wiredelta.backend.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<UserRole,Long> {

}
