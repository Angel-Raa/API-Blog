package org.caja.idea.service.interfaces;

import org.caja.idea.entity.dto.userDTO.AuthenticationRequest;
import org.caja.idea.entity.dto.userDTO.AuthenticationResponse;
import org.caja.idea.entity.dto.userDTO.AuthorizationRequest;
import org.caja.idea.entity.dto.userDTO.UserDTO;

import java.util.List;

public interface IUserService {
    AuthenticationResponse login(AuthorizationRequest request);
    AuthenticationResponse signup(AuthenticationRequest request);
    List<UserDTO> findAllUser();
}
