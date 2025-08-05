package com.games.games.services.interfaces;

import com.games.games.infrastructure.dto.UserRequest;
import com.games.games.infrastructure.dto.UserResponse;

import java.util.List;

public interface IUserService {
    UserResponse create(UserRequest req);
    List<UserResponse> getAll();
    UserResponse update(Long id, UserRequest req);
    void delete(Long id);
}