package com.example.webflux.domain.Authorization.models.permissions;

import java.util.UUID;

public final class PermissionsUsers {
    private final UUID userId;
    private final UUID permissionId;

    private PermissionsUsers(UUID userId, UUID permissionId) {
        this.userId = userId;
        this.permissionId = permissionId;
    }

    public static PermissionsUsers createNew(UUID userId, UUID permissionId) {
        return new PermissionsUsers(userId, permissionId);
    }

    public UUID getPermissionId() {
        return permissionId;
    }

    public UUID getUserId() {
        return userId;
    }
}
