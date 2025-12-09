package com.team3.what_if_workout.feed.service;

import com.team3.what_if_workout.feed.domain.Feed;
import com.team3.what_if_workout.feed.dto.request.FeedCreateDTO;
import com.team3.what_if_workout.feed.dto.request.FeedUpdateDTO;
import com.team3.what_if_workout.feed.dto.response.FeedResponseDTO;
import com.team3.what_if_workout.feed.repository.FeedRepository;
import com.team3.what_if_workout.feed.repository.ImageRepository;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.invoke.VarHandle;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepository feedRepository;
    private final MinioFileService minioFileService;

    @Transactional
    public FeedResponseDTO createFeed(FeedCreateDTO dto, Long userId, MultipartFile image) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String imageUrl = null;
        Feed feed = dto.toEntity();
        feed.assignUserId(userId);
        if(image != null && !image.isEmpty()) {
            imageUrl = minioFileService.saveImageFile(image);
            feed.attachImage(imageUrl);
        }
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
    public FeedResponseDTO updateFeed(Long feedId, FeedUpdateDTO dto, MultipartFile newImage, Long userId) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new IllegalArgumentException("해당 피드가 존재하지 않습니다."));
        // 권한 체크 (작성자 본인인지)
        if (!feed.getUserId().equals(userId)) {
            throw new IllegalStateException("수정 권한이 없습니다.");
        }
        feed.update(dto.feedTitle(), dto.feedContent());
        if(newImage != null && !newImage.isEmpty()) {
            if(feed.getImageUrl() != null) {
                minioFileService.deleteImageByUrl(feed.getImageUrl());
            }
            String newImageUrl = minioFileService.saveImageFile(newImage);
            feed.attachImage(newImageUrl);
        }
        return FeedResponseDTO.fromEntity(feed);
    }

    @Transactional
    public void deleteFeed(Long feedId, Long userId) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new IllegalArgumentException("해당 피드가 존재하지 않습니다."));
        // 본인 피드인지 검증 (Rule)
        if (!feed.getUserId().equals(userId)) {
            throw new IllegalStateException("본인의 피드만 삭제할 수 있습니다.");
        }
        if(feed.getImageUrl() != null) {
            minioFileService.deleteImageByUrl(feed.getImageUrl());
        }
        feedRepository.delete(feed);
    }
}
