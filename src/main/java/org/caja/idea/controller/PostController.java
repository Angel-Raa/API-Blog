package org.caja.idea.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.caja.idea.entity.dto.postDto.AuthenticationPostRequest;
import org.caja.idea.entity.dto.postDto.PostDTO;
import org.caja.idea.service.interfaces.IPostService;
import org.caja.idea.utils.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private IPostService service;
    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return ResponseEntity.ok(service.findAllPost());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@Valid @PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(service.findPostById(id));
    }
    @GetMapping("/find/{title}")
    public ResponseEntity<PostDTO> getPostByTitle(@Valid @PathVariable(value = "title") String title) {
        return ResponseEntity.ok(service.findPostByTitle(title));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createPost(@Valid @RequestBody PostDTO postDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createPost(postDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(service.update(postDTO, id));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deletePost(@Valid @PathVariable @Min(1) Long id, @RequestBody AuthenticationPostRequest request) {
        return ResponseEntity.ok(service.delete(id, request));
    }

}
