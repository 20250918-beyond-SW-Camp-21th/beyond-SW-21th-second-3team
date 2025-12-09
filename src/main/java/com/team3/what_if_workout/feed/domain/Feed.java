package com.team3.what_if_workout.feed.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedId;

    @Column(nullable = false)
    private Long userId; // Member.userId 참조 (FK)

    @Column(nullable = false)
    private String feedTitle;

    @Column(nullable = false)
    private String feedContent;

    private String imageUrl; //FeedImage.url 참조(FK)

    @Builder
    public Feed(String feedTitle,
                String feedContent,
                String imageUrl) {
        this.feedTitle = feedTitle;
        this.feedContent = feedContent;
        this.imageUrl = imageUrl;
    }

    public void assignUserId(Long userId) {
        this.userId = userId;
    }

    public void attachImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public void update(String feedTitle, String feedContent) {
        if (feedTitle != null) {
            this.feedTitle = feedTitle;
        }
        if (feedContent != null) {
            this.feedContent = feedContent;
        }
    }
}
