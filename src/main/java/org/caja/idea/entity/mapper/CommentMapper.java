package org.caja.idea.entity.mapper;

import org.caja.idea.entity.dto.commentDto.CommentDTO;
import org.caja.idea.entity.dto.commentDto.CommentListDto;
import org.caja.idea.entity.models.Comment;

public class CommentMapper {
    public static CommentDTO toDTO(Comment comment) {
        return new CommentDTO(comment.getUser().getUsername(),  comment.getBody());
    }
    public static Comment toEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setBody(commentDTO.body());
        return comment;
    }


    public static CommentListDto toListDTO(Comment comment) {
        return new CommentListDto(comment.getId(),comment.getUser().getUsername(), comment.getBody(), comment.getCreated());
    }
}
