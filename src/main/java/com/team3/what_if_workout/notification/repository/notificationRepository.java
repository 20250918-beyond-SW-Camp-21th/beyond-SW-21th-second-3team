package com.team3.what_if_workout.notification.repository;

import com.team3.what_if_workout.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                                             // 메인클래스의 자료형
public interface notificationRepository extends JpaRepository<Notification,Long>{

}
