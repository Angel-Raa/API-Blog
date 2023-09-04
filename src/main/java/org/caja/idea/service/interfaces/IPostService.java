package org.caja.idea.service.interfaces;

import java.util.List;

import org.caja.idea.entity.dto.postDto.AuthenticationPostRequest;
import org.caja.idea.entity.dto.postDto.PostDTO;
import org.caja.idea.entity.dto.postDto.PostListDto;
import org.caja.idea.utils.payload.ApiResponse;

public interface IPostService {
    List<PostDTO> findAllPost();
    PostDTO findPostById(Long postId);
    PostDTO findPostByTitle(String title);
    ApiResponse createPost(PostDTO postDTO);

    
    

}
