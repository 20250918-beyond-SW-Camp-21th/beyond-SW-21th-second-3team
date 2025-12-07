package com.team3.what_if_workout.notification.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Notification {
    @Id // PK
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long notificationId ; // 알림 ID

    @Column(nullable = false)
    Long userId ; // USER ID

    @Column(nullable = false)
    String content; // 알림 내용

    @Column(nullable = false)
    Long sendByUserId; // 보낸사람

    @Column
    Boolean checkNotification =false ; // 알람 확인여부

    @Builder
    public Notification(Long sendByUserID, Long userId, String content) {
        this.sendByUserId = sendByUserID;
        this.userId = userId;
        this.content = content;

    }
    public void update(Boolean check){
        this.checkNotification = check;
    }


}
