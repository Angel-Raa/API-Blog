package org.caja.idea.repository;

import org.caja.idea.entity.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IPostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByTitle(String title);
}
