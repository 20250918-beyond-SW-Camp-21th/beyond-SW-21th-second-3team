package com.team3.what_if_workout.feed.service;

import com.team3.what_if_workout.feed.domain.Feed;
import com.team3.what_if_workout.feed.dto.request.FeedCreateDTO;
import com.team3.what_if_workout.feed.dto.request.FeedUpdateDTO;
import com.team3.what_if_workout.feed.dto.response.FeedResponseDTO;
import com.team3.what_if_workout.feed.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.invoke.VarHandle;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepository feedRepository;

    @Transactional
    public FeedResponseDTO createFeed(FeedCreateDTO dto, Long userId) {
        Feed feed = dto.toEntity();
        feed.assignUserId(userId);
        Feed savedFeed = feedRepository.save(feed);
        return FeedResponseDTO.fromEntity(savedFeed);
    }

    public List<FeedResponseDTO> getFeedsByTitle(String title) {
        return feedRepository.findFeedsByFeedTitleContaining(title)
                .stream()
                .map(FeedResponseDTO::fromEntity)
                .toList();
    }

    public List<FeedResponseDTO> getFeedsByContent(String content) {
        return feedRepository.findFeedsByFeedContentContaining(content)
                .stream()
                .map(FeedResponseDTO::fromEntity)
                .toList();
    }

    public List<FeedResponseDTO> getFeedsByUserId(Long userId) {
        return feedRepository.findFeedsByUserId(userId)
                .stream()
                .map(FeedResponseDTO::fromEntity)
                .toList();
    }

    @Transactional
    public void updateFeed(Long feedId, FeedUpdateDTO dto, Long userId) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new IllegalArgumentException("해당 피드가 존재하지 않습니다."));
        // 권한 체크 (작성자 본인인지)
        if (!feed.getUserId().equals(userId)) {
            throw new IllegalStateException("수정 권한이 없습니다.");
        }
        // 부분 업데이트
        feed.update(dto.feedTitle(), dto.feedContent());
    }

    @Transactional
    public void deleteFeed(Long feedId, Long userId) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new IllegalArgumentException("해당 피드가 존재하지 않습니다."));
        // 본인 피드인지 검증 (Rule)
        if (!feed.getUserId().equals(userId)) {
            throw new IllegalArgumentException("본인의 피드만 삭제할 수 있습니다.");
        }
        feedRepository.delete(feed);
    }


}
