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

    @Builder
    public Feed(String feedTitle, String feedContent) {
        this.feedTitle = feedTitle;
        this.feedContent = feedContent;
    }

    public void assignUserId(Long userId) {
        this.userId = userId;
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
