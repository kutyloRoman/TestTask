package com.kutylo.testtask.mapper;

import com.kutylo.testtask.dto.request.UserRequest;
import com.kutylo.testtask.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User requestToModel(UserRequest userRequest);
}
