package kr.inha.technical.college.press.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.inha.technical.college.press.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	Member findByEmail(String email);

}
