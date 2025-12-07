package com.team3.what_if_workout.feed.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team3.what_if_workout.feed.domain.Feed;
import jakarta.validation.constraints.NotBlank;


public record FeedCreateDTO(
    @NotBlank(message = "피드 제목은 필수 사항입니다.")
//    @JsonProperty("feedTitle")
    String feedTitle,

    @NotBlank(message = "내용은 비워둘 수 없습니다.")
    String feedContent
    ) {
    public Feed toEntity(){
        return Feed.builder()
                .feedTitle(feedTitle)
                .feedContent(feedContent)
                .build();
    }

    //controller test 시 userID를 1L 받아오는 메서드하나 만들어서
}
