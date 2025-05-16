package com.unstage.core.welfarecenter.repository;

import com.unstage.core.welfarecenter.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}