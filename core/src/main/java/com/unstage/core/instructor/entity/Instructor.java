package com.unstage.core.instructor.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.unstage.core.jobposting.entity.BaseEntity;

@Entity
@Table(name = "instructors")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Instructor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String bio;

    private Instructor(String name, String email, String bio) {
        this.name = name;
        this.email = email;
        this.bio = bio;
    }

    public static Instructor create(String name, String email, String bio) {
        return new Instructor(name, email, bio);
    }

    public void update(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }
}