package com.wiredelta.backend.repository;

import com.wiredelta.backend.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobsRepository extends JpaRepository<Jobs,Long> {
}
