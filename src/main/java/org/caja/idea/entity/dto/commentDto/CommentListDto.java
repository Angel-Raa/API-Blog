package org.caja.idea.entity.dto.commentDto;

import java.time.LocalDateTime;

public record CommentListDto(Long id, String username, String body, LocalDateTime created) {
}
