package com.example.__WebFlux.infrastructure.security.mapper;

import com.example.__WebFlux.domain.user.models.UserModelDomain;
import com.example.__WebFlux.infrastructure.security.persistence.UserModelEntity;

public class UserMapper {

    public static UserModelDomain toDomain(UserModelEntity userModel) {
        return new UserModelDomain(userModel.getId(), userModel.getUsername(), userModel.getEmail(),
                userModel.getPassword(), userModel.getRols());
    }

    public static UserModelEntity toEntity(UserModelDomain userModel) {
        UserModelEntity userModelEntity = new UserModelEntity();
        userModelEntity.setId(userModel.getId());
        userModelEntity.setUsername(userModel.getUsername());
        userModelEntity.setEmail(userModel.getEmail());
        userModelEntity.setPassword(userModel.getPassword());
        userModelEntity.setRols(userModel.getRols());
        return userModelEntity;
    }
}
