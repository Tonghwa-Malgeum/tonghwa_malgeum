package com.unstage.core.jobposting.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "welfare_centers")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class WelfareCenter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String region;

    public WelfareCenter(String name, String region) {
        this.name = name;
        this.region = region;
    }
}