package com.team3.what_if_workout.notification.dto.request;

import com.team3.what_if_workout.notification.domain.Notification;
import lombok.Builder;


public record NotificationDTO(
        Long userID ,
        String content ,
        Long sendByUserId
)
{
    public Notification toEntity(){
        return Notification.builder()
                .userId(this.userID)
                .content(this.content)
                .sendByUserID(this.sendByUserId)
                .build();

    }




}
