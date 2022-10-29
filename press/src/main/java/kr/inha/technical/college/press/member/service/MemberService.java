package kr.inha.technical.college.press.member.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.inha.technical.college.press.member.constant.Role;
import kr.inha.technical.college.press.member.entity.Member;
import kr.inha.technical.college.press.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

	private final MemberRepository memberRepository;

	public Member saveMember(Member member) {
		validationDuplicateMember(member);
		return memberRepository.save(member);
	}

	public void addAdmin(Member member) {
		memberRepository.save(member);
	}

	public void deleteAdmin(String email) {
		Member member = memberRepository.findByEmail(email);
		member.setRole(Role.USER);
		memberRepository.save(member);
	}

	private void validationDuplicateMember(Member member) {

		Member findMember = memberRepository.findByEmail(member.getEmail());
		if (findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Member member = memberRepository.findByEmail(email);

		if (member == null) {
			throw new UsernameNotFoundException(email);
		}

		return User.builder().username(member.getEmail()).password(member.getPassword())
				.roles(member.getRole().toString()).build();

	}

	public Member findByEmail(String email) {
		Member list = memberRepository.findByEmail(email);
		return list;
	}

	public List<Member> findByRole(Role role) {
		List<Member> list = memberRepository.findByRole(role);
		return list;
	}

}
