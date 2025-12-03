package com.team3.what_if_workout.member.service;

import com.team3.what_if_workout.member.domain.Member;
import com.team3.what_if_workout.member.dto.request.SignupDTO;
import com.team3.what_if_workout.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member signup(SignupDTO dto){
        Member member = dto.toEntity();
        memberRepository.save(member);
        return member;
    }

}
