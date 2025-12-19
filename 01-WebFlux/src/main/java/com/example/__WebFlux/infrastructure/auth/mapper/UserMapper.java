package com.example.__WebFlux.infrastructure.auth.mapper;

import com.example.__WebFlux.domain.auth.models.UserModelDomain;
import com.example.__WebFlux.infrastructure.auth.persistence.UserModelEntity;

public class UserMapper {

    public static UserModelDomain toDomain(UserModelEntity userModel) {
        return new UserModelDomain(userModel.getId(), userModel.getUsername(), userModel.getEmail(),
                userModel.getPasswordHash(), userModel.getRols());
    }

    public static UserModelEntity toEntity(UserModelDomain userModel) {
        UserModelEntity userModelEntity = new UserModelEntity();
        userModelEntity.setId(userModel.getId());
        userModelEntity.setUsername(userModel.getUsername());
        userModelEntity.setEmail(userModel.getEmail());
        userModelEntity.setPasswordHash(userModel.getPassword());
        userModelEntity.setRols(userModel.getRols());
        return userModelEntity;
    }
}
