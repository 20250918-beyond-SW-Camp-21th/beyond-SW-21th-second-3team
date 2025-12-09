package com.team3.what_if_workout.feed.dto.response;

import com.team3.what_if_workout.feed.domain.Feed;

public record FeedResponseDTO(
        Long feedId,
        Long userId,
        String feedTitle,
        String feedContent
) {

    public static FeedResponseDTO fromEntity(Feed feed) {
        return new FeedResponseDTO(
                feed.getFeedId(),
                feed.getUserId(),
                feed.getFeedTitle(),
                feed.getFeedContent()
        );
    }
}
