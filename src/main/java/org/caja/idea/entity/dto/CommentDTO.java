package org.caja.idea.entity.dto;

import java.time.LocalDateTime;

public record CommentDTO(Long id, String body, LocalDateTime created,  Long users) {
}
