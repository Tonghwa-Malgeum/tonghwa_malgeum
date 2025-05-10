package com.unstage.core.jobposting.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_postings")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class JobPosting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "welfare_center_id", nullable = false,
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private WelfareCenter welfareCenter;

    @Column
    private LocalDateTime recruitmentStartDate;
    @Column
    private LocalDateTime recruitmentEndDate;

    public JobPosting(String title, WelfareCenter welfareCenter, LocalDateTime recruitmentStartDate, LocalDateTime recruitmentEndDate) {
        this.title = title;
        this.welfareCenter = welfareCenter;
        this.recruitmentStartDate = recruitmentStartDate;
        this.recruitmentEndDate = recruitmentEndDate;
    }
}