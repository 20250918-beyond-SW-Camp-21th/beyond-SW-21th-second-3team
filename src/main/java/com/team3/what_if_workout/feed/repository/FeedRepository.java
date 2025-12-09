package com.team3.what_if_workout.feed.repository;

import com.team3.what_if_workout.feed.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//jpa repository 옆에 있는건 엔티티, 옆에는 pk의 wrapper class
@Repository
public interface FeedRepository extends JpaRepository<Feed,Long> {
    List<Feed> findFeedsByFeedTitleContaining(String feedTitle);

    List<Feed> findFeedsByUserId(Long userId);

    List<Feed> findFeedsByFeedContentContaining(String feedContent);

//    @Query("CREATE m FROM Member m WHERE m")

}
