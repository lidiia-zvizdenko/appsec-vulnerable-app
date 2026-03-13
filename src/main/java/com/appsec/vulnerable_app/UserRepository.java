package com.appsec.vulnerable_app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    // VULNERABILITY: SQLi - raw string concatenation in query
    @Query(value = "SELECT * FROM users WHERE username = :username AND password = :password", nativeQuery = true)
    User findByUsernameSQLi(@Param("username") String username,
                            @Param("password") String password);

    // SAFE version
    User findByUsername(String username);
}