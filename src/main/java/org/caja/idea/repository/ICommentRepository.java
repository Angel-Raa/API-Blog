package org.caja.idea.repository;

import org.caja.idea.entity.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository  extends JpaRepository<Comment, Long> {
}
