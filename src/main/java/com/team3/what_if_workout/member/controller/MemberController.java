package com.team3.what_if_workout.member.controller;

import com.team3.what_if_workout.member.domain.Member;
import com.team3.what_if_workout.member.dto.reqeust.SignupDTO;
import com.team3.what_if_workout.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity registerUser(SignupDTO dto){
        Member member = memberService.signup(dto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
