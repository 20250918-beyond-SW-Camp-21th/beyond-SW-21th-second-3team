package com.team3.what_if_workout.qna.controller;

import com.team3.what_if_workout.qna.dto.request.AnswerCreateDTO;
import com.team3.what_if_workout.qna.dto.response.AnswerResponseDTO;
import com.team3.what_if_workout.qna.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
