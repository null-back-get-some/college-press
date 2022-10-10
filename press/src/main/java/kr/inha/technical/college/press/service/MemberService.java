package kr.inha.technical.college.press.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import kr.inha.technical.college.press.Repository.MemberRepository;
import kr.inha.technical.college.press.entity.Member;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	
	public Member saveMember(Member member) {
		validationDuplicateMember(member);
		return memberRepository.save(member);
	}

	private void validationDuplicateMember(Member member) {
		
		Member findMember = memberRepository.findByEmail(member.getEmail());
		if(findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
		
	}
	
}
