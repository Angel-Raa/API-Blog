package org.caja.idea.entity.role;

import java.util.Arrays;
import java.util.List;

public enum Role {
    USER(Arrays.asList(Permission.USER)),
    ADMINISTRATOR(Arrays.asList(Permission.USER, Permission.ADMIN));
    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
