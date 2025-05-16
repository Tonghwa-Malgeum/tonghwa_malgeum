package com.unstage.core.welfarecenter.repository;

import com.unstage.core.welfarecenter.entity.PostJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostJobRepository extends JpaRepository<PostJob, Long> {
}