package org.caja.idea.entity.dto.postDto;


import org.caja.idea.entity.models.Comment;

import java.time.LocalDateTime;
import java.util.List;

public record PostListDto(String username, String title, String content, LocalDateTime createAt, LocalDateTime updateAt, List<Comment> commentList) { }
