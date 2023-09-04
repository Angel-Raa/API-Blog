package org.caja.idea.service.implementation;

import org.caja.idea.entity.dto.commentDto.AuthenticationCommentRequest;
import org.caja.idea.entity.dto.commentDto.CommentDTO;
import org.caja.idea.entity.dto.commentDto.CommentListDto;
import org.caja.idea.entity.mapper.CommentMapper;
import org.caja.idea.entity.models.Comment;
import org.caja.idea.entity.models.Post;
import org.caja.idea.entity.models.Users;
import org.caja.idea.exception.CommentNotFoundException;
import org.caja.idea.exception.UnauthorizedException;
import org.caja.idea.exception.UserNotFoundException;
import org.caja.idea.repository.ICommentRepository;
import org.caja.idea.repository.IPostRepository;
import org.caja.idea.repository.IUserRepository;
import org.caja.idea.service.interfaces.ICommentService;
import org.caja.idea.utils.constants.Message;
import org.caja.idea.utils.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImplementation implements ICommentService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private ICommentRepository commentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CommentListDto> findCommentsAll() {
        return commentRepository.findAll()
                .stream()
                .map((dto) -> new CommentListDto(dto.getId(), dto.getUser().getUsername(), dto.getBody(),
                        dto.getCreated()))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CommentListDto findCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(Message.COMMENT_NOT_FOUND, 404, HttpStatus.NOT_FOUND,
                        LocalDateTime.now()));
        return CommentMapper.toListDTO(comment);
    }

    @Override
    @Transactional
    public ApiResponse createComment(Long postId, CommentDTO request) {
        System.out.println(request.body());
        // Obtener el usuario
        Users user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new UserNotFoundException(Message.USER_NOT_FOUND, 404, HttpStatus.NOT_FOUND,
                        LocalDateTime.now()));
        // Obtener el post al que se va a agregar el comentario
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CommentNotFoundException(Message.POST_NOT_FOUND, 404, HttpStatus.NOT_FOUND,
                        LocalDateTime.now()));
    
        // Crear el comentario
        Comment comment = new Comment();
        comment.setBody(request.body());
        comment.setPost(post);
        comment.setUser(user);
        commentRepository.save(comment);
        return new ApiResponse(Message.COMMENT_SAVE_SUCCESSFULLY, 201, HttpStatus.CREATED, LocalDateTime.now());
    }

    @Override
    @Transactional
    public CommentDTO updateComment(Long commentId, CommentDTO  request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(Message.COMMENT_NOT_FOUND, 404, HttpStatus.NOT_FOUND,
                        LocalDateTime.now()));
        // Verificar si el usuario tiene permisos para actualizar el comentario
        if (!comment.getUser().getUsername().equals(request.username())) {
            throw new UnauthorizedException(Message.USER_NOT_AUTHORIZED, 401, HttpStatus.UNAUTHORIZED
                    , LocalDateTime.now(), Message.COMMENT_NOT_AUTHORIZED_TO_UPDATE_COMMENT);
        }
        // Actualizar el contenido del comentario
        comment.setBody(request.body());
        commentRepository.save(comment);
        return CommentMapper.toDTO(comment);
    }

    @Override
    public ApiResponse deleteComment(Long commentId, AuthenticationCommentRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(Message.COMMENT_NOT_FOUND, 404, HttpStatus.NOT_FOUND,
                        LocalDateTime.now()));
        // Verificar si el usuario tiene permisos para eliminar el comentario
        if (!comment.getUser().getUsername().equals(request.getUsername())) {
            throw new UnauthorizedException(Message.USER_NOT_AUTHORIZED, 401, HttpStatus.UNAUTHORIZED,
                     LocalDateTime.now(), Message.COMMENT_NOT_AUTHORIZED_TO_DELETE_COMMENT);
        }
        // Eliminar el comentario
        commentRepository.delete(comment);
        return new ApiResponse(Message.COMMENT_DELETE_SUCCESSFULLY, 200, HttpStatus.OK, LocalDateTime.now());
    }
}
