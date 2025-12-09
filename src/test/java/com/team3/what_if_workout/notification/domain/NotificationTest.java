package com.team3.what_if_workout.notification.domain;

import com.team3.what_if_workout.notification.repository.NotificationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class NotificationTest {
// 엔티티 객체에서는 보통 검증하는게 생성이 잘 됐나, 내부의 메소드가 잘 작동하는지 검증한다 .
    @Test
    @DisplayName("알림 객체가 빌더로 정상실행 ( 생성 ) 되는지")
    void createNotification() {
        // Given ( 준비 ) 테스트에 필요한 데이터 , 초기 상태 설정
        Long sendById = 1L;
        Long receiverId = 2L;
        String content = "규원님이 알람을 보냈습니다";

        // when ( 실행 ) 실제 검증하고자하는 메소드 실행하기
        Notification notification = Notification.builder()
                .sendByUserID(sendById)
                .userId(receiverId)
                .content(content)
                .build();

        //then ( 검증 ) 실행 결과가 기대값과 일치 하는지 , 특정 행위가 발생 했는지 확인하기
        assertNotNull(notification);
        assertEquals(content, notification.getContent());
        assertEquals(receiverId,notification.getUserId());
        assertFalse(notification.getCheckNotification());
        // assertJ 로 바꿔보기
        assertThat(notification).isNotNull();
    }

    @Test
    @DisplayName("update 메소드 실행 테스트")
    void updateTest() {
        System.out.println("updateTest Test 실행");
        // Given   빌더로 Notification 객체 생성
        Notification notification = Notification.builder()
                .sendByUserID(1L) // 그냥 하드코딩해서 값 넣음
                .userId(2L)
                .content("알람 테스트")
                .build();
        // When
        // @beforeeach 걸어서 해보기
        notification.update(true);
        // Then (검증)
        // assertTrue(notification.getCheckNotification());  assertj 쓰지 않았을때 이렇게쓰고 아래는 썼을때
        assertThat(notification.getCheckNotification()).isTrue();
    }


}