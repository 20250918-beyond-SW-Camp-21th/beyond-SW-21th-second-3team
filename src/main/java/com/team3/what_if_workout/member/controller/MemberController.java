package com.team3.what_if_workout.member.controller;

import com.team3.what_if_workout.member.domain.Member;
import com.team3.what_if_workout.member.dto.request.SignupDTO;
import com.team3.what_if_workout.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입 API입니다.")
    @ApiResponse(responseCode = "201", description = "스케줄 조회 성공",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(
                            name = "회원가입 성공",
                            value = "회원가입 되었습니다."
                    )))
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity registerUser(SignupDTO dto) {
        Member member = memberService.signup(dto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
