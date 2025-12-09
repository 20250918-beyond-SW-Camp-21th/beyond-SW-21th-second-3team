package com.team3.what_if_workout.notification.service;

import com.team3.what_if_workout.notification.domain.Notification;
import com.team3.what_if_workout.notification.dto.request.NotificationDTO;
import com.team3.what_if_workout.notification.repository.NotificationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NotificationServiceTest {
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationRepository notificationRepository;

    @AfterEach
    void deleteAll(){
        notificationRepository.deleteAll();
    }

    // create 테스트
    @Test
    void createNotificationValidDtoCheck(){

        // given
        NotificationDTO dto = new NotificationDTO(
                3L,
                "DTO 테스트",
                2L);

        // when
        String message = notificationService.createNotification(dto);
        // then
        assertThat(message).isEqualTo("알림이 발송 되었습니다");
        List<Notification> notificationList = notificationRepository.findAllByUserId(3L);
        assertThat(notificationList.size()).isEqualTo(1);
    }

    // Read 테스트
    @Test
    void createNotificationUserIdExists(){
        //given
        Notification notification = Notification.builder()
                .userId(1L)
                .content("테스트 메세지")
                .sendByUserID(2L)
                .build();
        notificationRepository.save(notification);

        // when
        List<Notification> result = notificationRepository.findAllByUserId(2L);
        // then
        assertThat(result.size()).isEqualTo(1);
    }

    // Update 테스트
    @Test
    void updateCheck(){
        //given
        Notification notification = Notification.builder()
                .userId(1L)
                .content("제발True값으로 바뀌어라")
                .sendByUserID(2L)
                .build();
        notificationRepository.save(notification);
        //when
        notificationService.updateCheck(1L);
        //then
        Notification updated = notificationRepository.findAllByUserId(1L).get(0);
        assertThat(updated.getCheckNotification()).isTrue();
        // true 로 변경된다면 알람을 확인한것
    }

    // delete 테스트
    @Test
    void deleteNotification(){
        // given
        Notification notification = Notification.builder()
                .userId(1L)
                .content("삭제 하세요")
                .sendByUserID(2L)
                .build();
        notificationRepository.save(notification);

        // when
        notificationService.deleteNotification(notification.getNotificationId());

        // then
        assertThat(notificationRepository.existsById(notification.getNotificationId()))
                .isFalse();
    }
  
}