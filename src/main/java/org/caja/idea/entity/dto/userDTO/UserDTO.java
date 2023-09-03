package org.caja.idea.entity.dto.userDTO;

import java.time.LocalDateTime;

public record UserDTO(Long id, String username, String email, LocalDateTime createAt, LocalDateTime updateAt) { }
