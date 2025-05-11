package com.unstage.core.instructor.dto;

public record InstructorRequest(
        String name,
        String email,
        String bio
) {
}
