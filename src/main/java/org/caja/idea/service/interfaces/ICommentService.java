package org.caja.idea.service.interfaces;

import org.caja.idea.entity.dto.commentDto.AuthenticationCommentRequest;
import org.caja.idea.entity.dto.commentDto.CommentDTO;
import org.caja.idea.entity.dto.commentDto.CommentListDto;
import org.caja.idea.utils.payload.ApiResponse;

import java.util.List;

public interface ICommentService {
    List<CommentListDto> findCommentsAll();
    CommentListDto findCommentById(Long id);
    ApiResponse createComment(Long postId, CommentDTO request);
    CommentDTO updateComment(Long commentId, CommentDTO request);
    ApiResponse deleteComment(Long commentId, AuthenticationCommentRequest request);


}
