package com.springboot_tutorial.spring_workshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
//@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private LocalDate regDate;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "details_id")
    private Details userDetails;


    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AppUser(String username, String password, Details userDetails) {
        this(username, password);
        this.userDetails = userDetails;
    }

    @PrePersist
    public void prePersist() {
        regDate = LocalDate.now();
    }
}
