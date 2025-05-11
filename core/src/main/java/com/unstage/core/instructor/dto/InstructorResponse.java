package com.unstage.core.instructor.dto;

import com.unstage.core.instructor.entity.Instructor;

import java.time.LocalDateTime;

public record InstructorResponse(
        Long id,
        String name,
        String email,
        String bio,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate
) {
    public static InstructorResponse from(Instructor instructor) {
        return new InstructorResponse(
                instructor.getId(),
                instructor.getName(),
                instructor.getEmail(),
                instructor.getBio(),
                instructor.getCreatedDate(),
                instructor.getLastModifiedDate()
        );
    }
}