package com.kutylo.testtask.service;

import com.kutylo.testtask.dto.request.UserRequest;
import com.kutylo.testtask.model.User;

public interface UserService {
    User findUserByUsername(String username);

    void createNewUser(UserRequest userRequest);

}
