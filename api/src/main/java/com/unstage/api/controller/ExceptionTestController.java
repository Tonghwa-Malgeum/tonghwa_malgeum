package com.unstage.api.controller;

import com.unstage.core.exception.EntityNotFoundException;
import com.unstage.core.exception.UnstageException;
import com.unstage.core.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller for testing exception handling.
 * This controller is for demonstration purposes only and should be removed in production.
 */
@RestController
@RequestMapping("/api/v1/test/exceptions")
public class ExceptionTestController {

    /**
     * Throws an EntityNotFoundException.
     *
     * @param id the entity ID
     * @return never returns as it always throws an exception
     */
    @GetMapping("/entity-not-found/{id}")
    public ResponseEntity<String> testEntityNotFoundException(@PathVariable final Long id) {
        throw new EntityNotFoundException("User", id);
    }

    /**
     * Throws a ValidationException.
     *
     * @return never returns as it always throws an exception
     */
    @GetMapping("/validation")
    public ResponseEntity<String> testValidationException() {
        final Map<String, String> errors = new HashMap<>();
        errors.put("username", "Username is required");
        errors.put("email", "Email is invalid");

        throw new ValidationException("Validation failed for user registration", errors);
    }

    /**
     * Throws a generic UnstageException.
     *
     * @return never returns as it always throws an exception
     */
    @GetMapping("/generic")
    public ResponseEntity<String> testGenericException() {
        throw new UnstageException("Something went wrong");
    }

    /**
     * Throws a standard RuntimeException.
     *
     * @return never returns as it always throws an exception
     */
    @GetMapping("/runtime")
    public ResponseEntity<String> testRuntimeException() {
        throw new RuntimeException("Unexpected error occurred");
    }
}
