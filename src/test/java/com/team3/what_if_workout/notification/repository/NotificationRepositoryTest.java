package com.team3.what_if_workout.notification.repository;

import com.team3.what_if_workout.notification.domain.Notification;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


//        ┌───────────────────────────────────────────────┐
//        │                 TEST FLOW                     │
//        ├───────────────────────────────────────────────┤
//        │ 1. SpringBootTest로 실제 Repository 로딩       │
//        │                                               │
//        │ 2. @BeforeEach에서 테스트 데이터 DB에 저장       │
//        │    n1 → save()                                │
//        │    n2 → save()                                │
//        │                                               │
//        │ 3. @Test 실행                                  │
//        │    findAllByUserId(1L) 호출                    │
//        │      → JPA가 SQL 실행                          │
//        │      → DB에서 결과 리스트 반환                 │
//        │                                               │
//        │ 4. assert로 결과 검증                          │
//        │    - 리스트 크기 확인                          │
//        │    - 내용 확인                                 │
//        │                                               │
//        │ 5. 테스트 성공                                 │
//        └───────────────────────────────────────────────┘

@SpringBootTest
class NotificationRepositoryTest {
    //Test 클래스에선 롬복 사용하지 않으니 @Autowired 사용하여 의존성 주입
    @Autowired
    private NotificationRepository notificationRepo;
    // DB와 연결 되는지 테스트 하기위하여 Mockito 사용하여 @Repository 모의객체 생성?

    @BeforeEach // 테스트 전에 수행되어야 할 작업
    void setup(){
        Notification no1 = Notification.builder()
                .sendByUserID(3L)
                .userId(1L)
                .content("테스트1")
                .build();
        Notification no2 = Notification.builder()
                .sendByUserID(2L)
                .userId(1L)
                .content("테스트2")
                .build();

        notificationRepo.save(no1);
        notificationRepo.save(no2);
    }
    @AfterEach // 테스트 종료 후에 수행되어야할 작업
    void clean(){
        notificationRepo.deleteAll();
    }

    @Test
    void findAllUserId(){
        //when
        List<Notification> result = notificationRepo.findAllByUserId(1L);
        //then
        assertThat(result).hasSize(2);
        assertThat(result).extracting("content")
                .contains("테스트1","테스트2");
    }


}