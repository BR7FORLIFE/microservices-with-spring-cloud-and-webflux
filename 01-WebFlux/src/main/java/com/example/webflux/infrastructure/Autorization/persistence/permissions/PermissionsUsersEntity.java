package com.example.webflux.infrastructure.Autorization.persistence.permissions;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table("users_permissions")
@Data
public class PermissionsUsersEntity {
    @Id
    @Column("user_permission_id")
    private Integer userPermissionId;

    @Column("user_id")
    private UUID userId;

    @Column("permission_id")
    private UUID permissionId;
}
