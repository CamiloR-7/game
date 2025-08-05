package com.games.games.services;

import com.games.games.infrastructure.dto.UserRequest;
import com.games.games.infrastructure.dto.UserResponse;
import com.games.games.models.entities.User;
import com.games.games.infrastructure.exceptions.NotFoundException;
import com.games.games.models.repositories.UserRepository;
import com.games.games.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserResponse create(UserRequest req) {
        User user = new User();
        user.setNombre(req.getNombre());
        user.setDocumento(req.getDocumento());
        user.setCorreo(req.getCorreo());
        user.setNumeroTelefono(req.getNumeroTelefono());

        return mapToResponse(userRepository.save(user));
    }

    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UserResponse update(Long id, UserRequest req) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        user.setNombre(req.getNombre());
        user.setDocumento(req.getDocumento());
        user.setCorreo(req.getCorreo());
        user.setNumeroTelefono(req.getNumeroTelefono());

        return mapToResponse(userRepository.save(user));
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("Usuario no encontrado");
        }
        userRepository.deleteById(id);
    }

    private UserResponse mapToResponse(User user) {
        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setNombre(user.getNombre());
        res.setDocumento(user.getDocumento());
        res.setCorreo(user.getCorreo());
        res.setNumeroTelefono(user.getNumeroTelefono());
        return res;
    }
}