package org.caja.idea.entity.dto.postDto;


import java.time.LocalDateTime;

public record PostDTO( Long id,String username, String title, String content, LocalDateTime createAt, LocalDateTime updateAt) { }
