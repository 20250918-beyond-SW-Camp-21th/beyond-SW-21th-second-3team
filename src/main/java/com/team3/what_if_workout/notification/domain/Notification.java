package com.team3.what_if_workout.notification.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    @Id // PK
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long notificationId ; // 알림 ID

    @Column(nullable = false)
    Long userId ; // USER ID

    @Column(nullable = false)
    String content; // 알림 내용

    @Column(nullable = false)
    Long sendByUserID; // 보낸사람
}
