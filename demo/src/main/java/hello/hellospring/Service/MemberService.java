package hello.hellospring.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.hellospring.Domain.Member;
import hello.hellospring.repository.MemberRepository;

@Service
public class MemberService {

	private final MemberRepository memberRepository;
	
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	/**
	 * 회원 가임
	 */
	public Long join(Member member) {
		validName(member);	//같은 이름이 있는 중복 회원X
		memberRepository.save(member);
		return member.getId();
	}

	private void validName(Member member) {
		memberRepository.findByName(member.getName())
			.ifPresent(m -> {
				throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
	}
	
	/**
	 * 전체 회원 조회
	 */
	public List<Member> findManagers() {
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}
}
