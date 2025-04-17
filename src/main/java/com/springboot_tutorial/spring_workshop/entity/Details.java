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
//@Table(name = "details")
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String email;
    private String name;
    private LocalDate birthday;


    public Details(String email, String name, LocalDate birthday) {
        this.email = email;
        this.name = name;
        this.birthday = birthday;
    }

}
