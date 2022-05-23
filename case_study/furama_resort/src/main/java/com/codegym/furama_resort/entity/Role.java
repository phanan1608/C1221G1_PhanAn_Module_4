package com.codegym.furama_resort.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    private String roleName;

    @ManyToMany(mappedBy = "roleSet")
    private Set<User> userSet;


    public Role() {
    }

    public Role(Integer roleId, String roleName, Set<User> userSet) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.userSet = userSet;
    }


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}
