-- Instructors Data
INSERT INTO instructors (id, name, email, bio, created_date, last_modified_date) 
VALUES (1, 'Test Instructor 1', 'instructor1@example.com', 'Bio for instructor 1', NOW(), NOW());
INSERT INTO instructors (id, name, email, bio, created_date, last_modified_date) 
VALUES (2, 'Test Instructor 2', 'instructor2@example.com', 'Bio for instructor 2', NOW(), NOW());

-- Portfolios Data
INSERT INTO portfolios (id, title, content, instructor_id, created_date, last_modified_date) 
VALUES (1, 'Portfolio 1', 'Content for portfolio 1', 1, NOW(), NOW());
INSERT INTO portfolios (id, title, content, instructor_id, created_date, last_modified_date) 
VALUES (2, 'Portfolio 2', 'Content for portfolio 2', 1, NOW(), NOW());
INSERT INTO portfolios (id, title, content, instructor_id, created_date, last_modified_date) 
VALUES (3, 'Portfolio 3', 'Content for portfolio 3', 2, NOW(), NOW());