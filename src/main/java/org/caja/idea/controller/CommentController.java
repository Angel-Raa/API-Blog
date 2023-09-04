package org.caja.idea.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.caja.idea.entity.dto.commentDto.AuthenticationCommentRequest;
import org.caja.idea.entity.dto.commentDto.CommentDTO;
import org.caja.idea.entity.dto.commentDto.CommentListDto;
import org.caja.idea.service.interfaces.ICommentService;
import org.caja.idea.utils.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @GetMapping("/all")
    public ResponseEntity<List<CommentListDto>> findCommentsAll() {
        return ResponseEntity.ok(commentService.findCommentsAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentListDto> findCommentById(@Valid @PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(commentService.findCommentById(id));
    }

    @PostMapping("/create/{postId}")
    public ResponseEntity<ApiResponse> createComment(@Valid @PathVariable(value = "postId") @Min(1) Long postId, @RequestBody  CommentDTO request) {
        return ResponseEntity.ok(commentService.createComment(postId, request));
    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@Valid @PathVariable(value = "commentId") @Min(1) Long commentId, @RequestBody  CommentDTO request) {
        return ResponseEntity.ok(commentService.updateComment(commentId, request));
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@Valid @PathVariable(value = "commentId") @Min(1) Long commentId, @RequestBody AuthenticationCommentRequest request) {
        return ResponseEntity.ok(commentService.deleteComment(commentId, request));
    }
}
