package org.caja.idea.service.implementation;

import org.caja.idea.entity.dto.postDto.AuthenticationPostRequest;
import org.caja.idea.entity.dto.postDto.PostDTO;
import org.caja.idea.entity.dto.postDto.PostListDto;
import org.caja.idea.entity.mapper.PostMapper;
import org.caja.idea.entity.models.Post;
import org.caja.idea.entity.models.Users;
import org.caja.idea.exception.PostNotFoundException;
import org.caja.idea.exception.TitleNotFoundException;
import org.caja.idea.exception.UnauthorizedException;
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
    private IPostRepository repository;
    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<PostListDto> allPost() {
        return repository.findAll().stream()
                .map((dto) -> new PostListDto(dto.getUsers().getUsername(),
                        dto.getTitle(), dto.getContent(), dto.getCreated(),
                        dto.getUpdated(), dto.getComments().stream().toList())).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> findAllPost() {
        return repository.findAll()
                .stream()
                .map((dto) -> new PostDTO(dto.getId(),dto.getUsers().getUsername(), dto.getTitle(), dto.getContent(),
                        dto.getCreated(), dto.getUpdated()))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PostDTO findPostById(Long postId) {
        Post post = repository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(Message.POST_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now()));
        return PostMapper.toPostDto(post);
    }

    @Override
    @Transactional(readOnly = true)
    public PostDTO findPostByTitle(String title) {
        Post post = repository.findByTitle(title)
                .orElseThrow(() -> new TitleNotFoundException(Message.TITLE_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now()));
        return PostMapper.toPostDto(post);
    }


    @Override
    @Transactional
    public ApiResponse createPost(PostDTO postDTO) {
        Users users = userRepository.findByUsername(postDTO.username())
                .orElseThrow(() -> new UserNotFoundException(Message.USER_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now()));
        Post post = PostMapper.toPostDto(postDTO, users);
        repository.save(post);
        return new ApiResponse(Message.POST_SAVE_SUCCESSFULLY,201, HttpStatus.CREATED, LocalDateTime.now());
    }



    @Override
    @Transactional
    public PostDTO update(PostDTO postDTO, Long postId) {
        Post post = repository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(Message.POST_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now()));
        // Verificar si el usuario actual coincide con el autor del post
        if(!post.getUsers().getUsername().equals(postDTO.username())){
            throw  new UnauthorizedException(Message.USER_NOT_AUTHORIZED, 401, HttpStatus.UNAUTHORIZED,  Message.WITHOUT_PERMISSION, LocalDateTime.now());
        }
        // Actualizar los campos del post con los valores proporcionados en postDTO
        post.setTitle(postDTO.title());
        post.setContent(postDTO.content());
        repository.save(post);
       return PostMapper.toPostDto(post);
    }

    @Override
    @Transactional
    public ApiResponse delete(Long postId, AuthenticationPostRequest request) {
        Post post = repository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(Message.POST_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now()));
        if(!post.getUsers().getUsername().equals(request.getUsername())){
            throw new UnauthorizedException(Message.UNAUTHORIZED_DELETE_MESSAGE, 401, HttpStatus.UNAUTHORIZED,  Message.UNAUTHORIZED_PERMISSION_MESSAGE, LocalDateTime.now());
        }
        repository.delete(post);
        return new ApiResponse(Message.POST_DELETE_SUCCESSFULLY, 200, HttpStatus.OK, LocalDateTime.now());
    }


}
