package org.caja.idea.entity.mapper;

import org.caja.idea.entity.dto.PostDTO;
import org.caja.idea.entity.models.Post;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {
    @Mapping(target = "id", ignore = true)
    Post toPost(PostDTO postDto);
    PostDTO toPostDto(Post post);
    @Mappings({

            @Mapping(target = "id", ignore = true),
            @Mapping(target = "author",  ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "updated", ignore = true),
            @Mapping(target = "content", source = "dto.content"),
            @Mapping(source = "title", target = "dto.title"),
    })
    Post updatePostFromDto(PostDTO dto, @MappingTarget Post post);
    @InheritInverseConfiguration
    PostDTO mapToPostDto(Post post);
}
