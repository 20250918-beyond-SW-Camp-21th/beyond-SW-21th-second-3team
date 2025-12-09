package com.team3.what_if_workout.feed.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team3.what_if_workout.feed.domain.Feed;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;


public record FeedCreateDTO(
        @Schema(description = "피드 제목", example = "오늘 운동")
    @NotBlank(message = "피드 제목은 필수 사항입니다.")
//    @JsonProperty("feedTitle")
    String feedTitle,

    @Schema(description = "피드 내용", example = "오운완")
    @NotBlank(message = "내용은 비워둘 수 없습니다.")
    String feedContent
    ) {
    public Feed toEntity(){
        return Feed.builder()
                .feedTitle(feedTitle)
                .feedContent(feedContent)
                .imageUrl(null)
                .build();
    }
}
