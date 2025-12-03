package com.team3.what_if_workout.member.repository;

import com.team3.what_if_workout.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
