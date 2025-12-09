package com.team3.what_if_workout.notification.repository;

import com.team3.what_if_workout.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// DB와 대화하는 곳
@Repository                                             // 메인클래스의 자료형
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findAllByUserId(Long userId);
    Notification findAllByUserIdAndCheckNotificationIsFalse(Long userId);


}

