package com.team3.what_if_workout.qna.controller;

import com.team3.what_if_workout.qna.dto.request.QuestionCreateDTO;
import com.team3.what_if_workout.qna.dto.request.QuestionUpdateDTO;
import com.team3.what_if_workout.qna.dto.response.QuestionResponseDTO;
import com.team3.what_if_workout.qna.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @Operation(summary = "질문 작성 API 입니다.")
    @PostMapping
    public ResponseEntity<QuestionResponseDTO> create(@RequestBody QuestionCreateDTO dto) {
        Long userId = 1L;
        QuestionResponseDTO response = questionService.createQuestion(dto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "질문 검색 API 입니다.")
    @GetMapping("/search")
    public ResponseEntity<List<QuestionResponseDTO>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(questionService.searchQuestions(keyword));
    }

    @Operation(summary = "내 질문 찾기 API 입니다.")
    @GetMapping("/search/myQuestion")
    public ResponseEntity<List<QuestionResponseDTO>> getMyQuestion(@RequestHeader(("userId")) Long userId) {
        return ResponseEntity.ok(questionService.getMyQuestions(userId));
    }

    @Operation(summary = "질문 수정 API 입니다.")
    @PatchMapping("/{questionId}")
    public ResponseEntity<Void> updateQuestion(
            @PathVariable Long questionId,
            @RequestBody QuestionUpdateDTO dto) {
        Long userId = 1L;
        questionService.updateQuestion(questionId, dto, userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "질문 삭제 API입니다.")
    @DeleteMapping("{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        Long userId = 1L;
        questionService.deleteQuestion(questionId, userId);
        return ResponseEntity.noContent().build();
    }
}
