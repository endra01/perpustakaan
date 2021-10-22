package com.sinaukoding.perpustakaan.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Setter
@Getter
@NoArgsConstructor

public class User extends BaseEntity<User> {


    private static final long serialVersionUID = 3811271616808323398L;

    public enum Role{
        ROLE_USER,
        ROLE_ADMIN
    }
    @Column(name = "role", columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    @Column(name = "profil_name", columnDefinition = "VARCHAR(100)", nullable = false)
    private String profilName;

    @Column(name = "username", columnDefinition = "VARCHAR(50)", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "token")
    private String token;
}
