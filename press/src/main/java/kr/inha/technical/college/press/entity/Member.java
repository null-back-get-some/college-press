package kr.inha.technical.college.press.entity;

import javax.persistence.*;

import org.springframework.security.crypto.password.PasswordEncoder;

import groovy.transform.ToString;
import kr.inha.technical.college.press.constant.Role;
import kr.inha.technical.college.press.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_MEMBER")
@Getter
@Setter
@ToString
public class Member {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String address;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		
		Member member = new Member();
		
		member.setName(memberFormDto.getName());
		member.setEmail(memberFormDto.getEmail());
		member.setAddress(memberFormDto.getAddress());
		String password = passwordEncoder.encode(memberFormDto.getPassword());
		member.setPassword(password);
		member.setRole(Role.USER);
		
		return member;
		
	}
	
	
}
