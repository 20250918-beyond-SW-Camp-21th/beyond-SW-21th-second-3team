package com.team3.what_if_workout.notification.service;

import com.team3.what_if_workout.notification.domain.Notification;
import com.team3.what_if_workout.notification.dto.request.NotificationDTO;
import com.team3.what_if_workout.notification.repository.NotificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
/*서비스에는 비즈니스 로직을 작성 여기가 로직..?
* DB에 접근하여 데이터를 추가, 삭제, 수정, 선택과 같은 요청을 처리할 수 있어야함*/

public class NotificationService {
    // service 는 DB에 접근하기 위하여 @Repository에 요청하여 DB로 부터 필요한 값을 가져옴
    private final NotificationRepository notificationRepository;

    Notification notification;

    @Transactional
    public String createNotification(NotificationDTO dto){
        Notification notification = dto.toEntity();
        notificationRepository.save(notification);

        return "알림이 발송 되었습니다";
    }

    public List<Notification> findAllNotification(Long userId){
        List<Notification> notificationList =  notificationRepository.findAllByUserId(userId);

        return notificationList;
    }


    public void updateCheck(Long Id) {
        Notification notification = notificationRepository.findAllByUserIdAndCheckNotificationIsFalse(Id);
        notification.update(true);
    }

    public void deleteNotification(Long notificationId){
        notificationRepository.deleteById(notificationId);
    }

}
