package com.team3.what_if_workout.qna.controller;

import com.team3.what_if_workout.qna.dto.request.QuestionCreateDTO;
import com.team3.what_if_workout.qna.dto.response.QuestionResponseDTO;
import com.team3.what_if_workout.qna.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @Operation(summary = "질문 작성 API 입니다.")
    @PostMapping
    public ResponseEntity<QuestionResponseDTO> create(@RequestBody QuestionCreateDTO dto) {
        Long userId = 1L;
        QuestionResponseDTO response = questionService.createQuestion(dto,userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
