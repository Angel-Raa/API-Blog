package org.caja.idea.entity.dto.postDto;


import java.time.LocalDateTime;

public record PostDTO(String username, Long id, String title, String content, LocalDateTime createAt, LocalDateTime updateAt) { }
