package com.example.webflux.domain.Authorization.models.rols;

import java.util.UUID;

public final class RolsUsers {
    private final UUID rolId;
    private final UUID userId;

    private RolsUsers(UUID rolId, UUID userId) {
        this.rolId = rolId;
        this.userId = userId;
    }

    public static RolsUsers createNew(UUID rolId, UUID userId) {
        return new RolsUsers(rolId, userId);
    }

    public UUID getRolId() {
        return rolId;
    }

    public UUID getUserId() {
        return userId;
    }
}
