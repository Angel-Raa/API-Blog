package org.caja.idea.entity.dto;

import org.caja.idea.entity.models.Users;


import java.time.LocalDateTime;

public record CommentDTO(Long id, String body, LocalDateTime created) {
}
