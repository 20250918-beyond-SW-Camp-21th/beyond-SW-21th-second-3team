package com.team3.what_if_workout.qna.controller;

import com.team3.what_if_workout.qna.dto.request.AnswerCreateDTO;
import com.team3.what_if_workout.qna.dto.request.AnswerUpdateDTO;
import com.team3.what_if_workout.qna.dto.response.AnswerResponseDTO;
import com.team3.what_if_workout.qna.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @Operation(summary = "답변 작성 API 입니다.")
    @PostMapping
    public ResponseEntity<AnswerResponseDTO> create(@RequestBody AnswerCreateDTO dto) {
        Long userId = 1L;
        Long questionId = 1L;
        AnswerResponseDTO response = answerService.createAnswer(dto, userId, questionId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "내용으로 답변 검색 API 입니다.")
    @GetMapping("/search/content")
    public ResponseEntity<List<AnswerResponseDTO>> searchByContent(@RequestParam String content) {
        return ResponseEntity.ok(answerService.getAnswersByAnswerContent(content));
    }

    @Operation(summary = "questionId로 답변 검색 API 입니다.")
    @GetMapping("/search/questionId")
    public ResponseEntity<List<AnswerResponseDTO>> searchByQuestionId(@RequestParam Long questionId) {
        return ResponseEntity.ok(answerService.getAnswersByQuestionId(questionId));
    }

    @Operation(summary = "답변 수정 API 입니다.")
    @PatchMapping("/{answerId}")
    public ResponseEntity<Void> updateAnswe(
            @PathVariable Long answerId,
            @RequestBody AnswerUpdateDTO dto) {
        Long userId = 1L;
        Long questionId = 1L;
        answerService.updateAnswer(answerId, dto, userId, questionId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "답변 삭제 API 입니다.")
    @DeleteMapping("{answerId}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long answerId) {
        Long userId = 1L;
        Long questionId = 1L;
        answerService.deleteAnswer(answerId, userId, questionId);
        return ResponseEntity.noContent().build();
    }
}
