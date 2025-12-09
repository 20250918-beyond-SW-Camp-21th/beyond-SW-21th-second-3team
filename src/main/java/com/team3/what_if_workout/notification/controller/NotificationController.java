package com.team3.what_if_workout.notification.controller;

import com.team3.what_if_workout.notification.dto.request.NotificationDTO;
import com.team3.what_if_workout.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController // 1. API 요청을 처리하는 컨트롤러임을 명시 (이거 없으면 일반 클래스 취급됨)
@RequestMapping("/notification")
public class NotificationController {

    // 3. 서비스 가져오기 RequiredArgsConstructor 필수 집가서 이해
    private final NotificationService notificationService;

    @PostMapping("/notification")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> notification(@RequestBody NotificationDTO dto){

        // 4. 컨트롤러가 직접 변환 X 서비스한테 저장
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.createNotification(dto));
    }

    @PatchMapping("/notification/{id}")
    public ResponseEntity<Void> statusUpdate(@PathVariable Long id ){
        notificationService.updateCheck(id);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/notification/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id){
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}