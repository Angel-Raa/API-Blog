package org.caja.idea.entity.mapper;

import org.caja.idea.entity.dto.CommentDTO;
import org.caja.idea.entity.models.Comment;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    Comment toComment(CommentDTO commentDto);
    CommentDTO toCommentDto(Comment comment);
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(source = "dto.body", target = "body"),
    })
    Comment updateCommentFromDto(CommentDTO dto, @MappingTarget Comment comment);
    // Mapeo inverso para evitar duplicar código
    @InheritInverseConfiguration
    CommentDTO mapToCommentDto(Comment comment);
    
}
