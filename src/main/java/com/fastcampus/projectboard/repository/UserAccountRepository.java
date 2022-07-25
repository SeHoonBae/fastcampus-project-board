package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.dto.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}