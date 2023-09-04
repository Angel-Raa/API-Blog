package org.caja.idea.service.implementation;

import org.caja.idea.entity.dto.postDto.AuthenticationPostRequest;
import org.caja.idea.entity.dto.postDto.PostDTO;
import org.caja.idea.entity.mapper.PostMapper;
import org.caja.idea.entity.models.Post;
import org.caja.idea.entity.models.Users;
import org.caja.idea.exception.PostNotFoundException;
import org.caja.idea.exception.UserNotFoundException;
import org.caja.idea.repository.IPostRepository;
import org.caja.idea.repository.IUserRepository;
import org.caja.idea.service.interfaces.IPostService;
import org.caja.idea.utils.constants.Message;
import org.caja.idea.utils.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImplementation  implements IPostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private IPostRepository repository;
    @Autowired
    private IUserRepository userRepository;
    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> findAllPost() {
        return repository.findAll()
                .stream()
                .map((dto) -> new PostDTO(dto.getUsers().getUsername(), dto.getId(), dto.getTitle(), dto.getContent(),
                        dto.getCreated(), dto.getUpdated()))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PostDTO findPostById(Long postId) {
        Post post = repository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(Message.POST_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now()));
        return new PostDTO(post.getUsers().getUsername(),post.getId(), post.getTitle(), post.getContent(), post.getCreated(), post.getUpdated());
    }

    @Override
    @Transactional(readOnly = true)
    public PostDTO findPostByTitle(String title) {
        Post post = repository.findByTitle(title)
                .orElseThrow(() -> new PostNotFoundException(Message.TITLE_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now()));
        return new PostDTO(post.getUsers().getUsername(),post.getId(), post.getTitle(), post.getContent(), post.getCreated(), post.getUpdated());
    }

    @Override
    @Transactional
    public ApiResponse createPost(PostDTO postDTO) {
        Users users = userRepository.findByUsername(postDTO.username())
                .orElseThrow(() -> new UserNotFoundException(Message.USER_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now()));
        Post post = new Post();
        post.setTitle(postDTO.title());
        post.setContent(postDTO.content());
        post.setUsers(users);
        repository.save(post);
        return new ApiResponse(Message.POST_SAVE_SUCCESSFULLY,201, HttpStatus.CREATED, LocalDateTime.now());
    }

}
