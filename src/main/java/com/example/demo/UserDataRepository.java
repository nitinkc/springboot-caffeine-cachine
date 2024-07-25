package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, Long> {

    UserData findByUserIdAndUsername(Long userId, String username);
}