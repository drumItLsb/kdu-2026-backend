package com.example.talentPortal.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_users_username", columnNames = "user_name"),
                @UniqueConstraint(name = "uk_users_email", columnNames = "email")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false, length = 60)
    private String userName;

    @Column(nullable = false)
    private String password; // Stored as BCrypt hash only

    @Column(nullable = false, length = 120)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    private List<String> roles = new ArrayList<>();

    public User() {}

    public User(String userName, String password, String email, List<String> roles) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() { return id; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }
}

