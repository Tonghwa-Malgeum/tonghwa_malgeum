package com.unstage.core.post.repository;

import com.unstage.core.post.entity.PostJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostJobRepository extends JpaRepository<PostJob, Long> {
}