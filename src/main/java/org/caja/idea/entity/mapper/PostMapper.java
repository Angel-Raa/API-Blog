package org.caja.idea.entity.mapper;

import org.caja.idea.entity.dto.postDto.PostDTO;
import org.caja.idea.entity.models.Post;
import org.caja.idea.entity.models.Users;

public class PostMapper {
    public static PostDTO toPostDto(Post post) {
        return new PostDTO( post.getId(),post.getUsers().getUsername(), post.getTitle(), post.getContent(), post.getCreated(), post.getUpdated());
    }

    public static Post toPostDto(PostDTO postDTO, Users users) {
        Post post = new Post();
        post.setTitle(postDTO.title());
        post.setContent(postDTO.content());
        post.setUsers(users);
        return post;
    }


}
