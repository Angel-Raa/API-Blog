package org.caja.idea.entity.dto;

import java.time.LocalDateTime;

public record PostDTO(Long id, String title, String content, LocalDateTime created, LocalDateTime updated, Long author) {
}
