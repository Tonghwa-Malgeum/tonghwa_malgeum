package com.unstage.core.instructor.component;

import com.unstage.core.exception.EntityNotFoundException;
import com.unstage.core.instructor.entity.Instructor;
import com.unstage.core.instructor.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InstructorReader {

    private final InstructorRepository instructorRepository;

    public Instructor read(final Long instructorId) {
        log.debug("강사 조회 실행 - id={}", instructorId);

        final Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new EntityNotFoundException("Instructor", instructorId));

        log.debug("강사 조회 완료 - id={}, name={}", instructor.getId(), instructor.getEmail());

        return instructor;
    }

    public Instructor save(final Instructor instructor) {
        log.debug("강사 저장 실행 - name={}, email={}", instructor.getName(), instructor.getEmail());

        Instructor savedInstructor = instructorRepository.save(instructor);

        log.debug("강사 저장 완료 - id={}, name={}", savedInstructor.getId(), savedInstructor.getName());

        return savedInstructor;
    }
}