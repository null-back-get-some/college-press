package kr.inha.technical.college.press.member.service;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.inha.technical.college.press.member.entity.Member;
import kr.inha.technical.college.press.member.repository.MemberRepository;
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
	
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		
		Member member = memberRepository.findByEmail(email);
		
		if(member == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return User.builder()
				.username(member.getEmail())
				.password(member.getPassword())
				.roles(member.getRole().toString())
				.build();
		
	}
	
}
