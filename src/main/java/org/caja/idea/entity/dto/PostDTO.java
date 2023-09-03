package org.caja.idea.entity.dto;

import org.caja.idea.entity.models.Users;

import java.time.LocalDateTime;

public record PostDTO(Long id, String title, String content, LocalDateTime created, LocalDateTime updated, Users users) {
}
