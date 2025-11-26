package com.team3.what_if_workout.member.domain;

import com.team3.what_if_workout.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer goalsCalories;

    @Column(nullable = false)
    private String role;

    @Builder
    public Member(String id, String password, Integer goalsCalories, String role) {
        this.id = id;
        this.password = password;
        this.goalsCalories = goalsCalories;
        this.role = role;
    }
}
