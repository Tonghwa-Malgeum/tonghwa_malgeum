package com.unstage.core.instructor.service;

import com.unstage.core.instructor.component.InstructorReader;
import com.unstage.core.instructor.entity.Instructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorReader instructorReader;

    @Transactional(readOnly = true)
    public Instructor getInstructor(final Long id) {
        return instructorReader.read(id);
    }

    @Transactional
    public Instructor createInstructor(final String name, final String email, final String bio) {
        final Instructor instructor = Instructor.create(name, email, bio);
        return instructorReader.save(instructor);
    }

    @Transactional
    public Instructor updateInstructor(final Long id, final String name, final String bio) {
        final Instructor instructor = getInstructor(id);
        instructor.update(name, bio);
        return instructor;
    }

    @Transactional
    public void deleteInstructor(final Long id) {
        final Instructor instructor = getInstructor(id);
        instructor.softDelete(java.time.LocalDateTime.now());
    }
}
