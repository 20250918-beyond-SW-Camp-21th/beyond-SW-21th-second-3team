package com.team3.what_if_workout.notification.service;

import com.team3.what_if_workout.member.domain.Member;
import com.team3.what_if_workout.member.repository.MemberRepository;
import com.team3.what_if_workout.notification.domain.Notification;
import com.team3.what_if_workout.notification.dto.request.NotificationDTO;
import com.team3.what_if_workout.notification.repository.notificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Notation;

@Service
@RequiredArgsConstructor
/*서비스에는 비즈니스 로직을 작성 여기가 로직..?
* DB에 접근하여 데이터를 추가, 삭제, 수정, 선택과 같은 요청을 처리할 수 있어야함*/

public class NotificationService {
    // service 는 DB에 접근하기 위하여 @Repository에 요청하여 DB로 부터 필요한 값을 가져옴
    private final notificationRepository notificationrepository;

    @Transactional
    public Notification notification1(NotificationDTO dto){
        Notification notification = dto.toEntity();
        notificationrepository.save(notification);
        return notification;

    }

}
