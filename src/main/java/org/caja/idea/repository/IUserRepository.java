package org.caja.idea.repository;

import org.caja.idea.entity.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface IUserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
