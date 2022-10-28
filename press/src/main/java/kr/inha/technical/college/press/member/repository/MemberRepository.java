package kr.inha.technical.college.press.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.inha.technical.college.press.member.constant.Role;
import kr.inha.technical.college.press.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	Member findByEmail(String email);

	Member findByRole(Role role);
}
