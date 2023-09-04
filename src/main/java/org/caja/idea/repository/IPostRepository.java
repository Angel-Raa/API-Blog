package org.caja.idea.repository;

import org.caja.idea.entity.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IPostRepository extends JpaRepository<Post, Long> {
}
