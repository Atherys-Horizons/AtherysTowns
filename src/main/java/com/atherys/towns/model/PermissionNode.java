package com.atherys.towns.model;

import com.atherys.core.db.Identifiable;
import com.atherys.towns.api.Permission;
import com.atherys.towns.persistence.converter.PermissionConverter;

import javax.annotation.Nonnull;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class PermissionNode implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String contextId;

    @Convert(converter = PermissionConverter.class)
    private Permission permission;

    private boolean permitted;

    @Nonnull
    @Override
    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public boolean isPermitted() {
        return permitted;
    }

    public void setPermitted(boolean permitted) {
        this.permitted = permitted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionNode that = (PermissionNode) o;
        return permitted == that.permitted &&
                id.equals(that.id) &&
                userId.equals(that.userId) &&
                contextId.equals(that.contextId) &&
                permission.equals(that.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, contextId, permission, permitted);
    }

    /*

     +------------------------------------------------+--------------------------------------------+------------+-----------+
     | UserId                                         | ContextId                                  | Permission | Permitted |
     +------------------------------------------------+--------------------------------------------+------------+-----------+
     | Town{349ed239-ec0f-457b-b6eb-247551219f7b}     | Town{349ed239-ec0f-457b-b6eb-247551219f7b} | Build      | true      |
     | Town{349ed239-ec0f-457b-b6eb-247551219f7b}     | Town{349ed239-ec0f-457b-b6eb-247551219f7b} | Destroy    | true      |
     | Resident{641a6ce8-6f89-4513-bbb4-107aa3f51923} | Town{349ed239-ec0f-457b-b6eb-247551219f7b} | Destroy    | false     |
     +------------------------------------------------+--------------------------------------------+------------+-----------+

     */
}