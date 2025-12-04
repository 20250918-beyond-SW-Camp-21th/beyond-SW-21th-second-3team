package com.team3.what_if_workout.notification.controller;

import com.team3.what_if_workout.notification.domain.Notification;
import com.team3.what_if_workout.notification.dto.request.NotificationDTO;
import com.team3.what_if_workout.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // 1. API 요청을 처리하는 컨트롤러임을 명시 (이거 없으면 일반 클래스 취급됨)
@RequiredArgsConstructor // 2. final이 붙은 필드(Service)를 자동으로 연결(생성자 주입)해줌
public class NotificationController {

    // 3. 서비스 가져오기
    private final NotificationService notificationService;

    @PostMapping("/notification")
    public Notification notification(@RequestBody NotificationDTO dto){

        // 4. 컨트롤러가 직접 변환 X 서비스한테 저장
        return notificationService.notification1(dto);
    }
}