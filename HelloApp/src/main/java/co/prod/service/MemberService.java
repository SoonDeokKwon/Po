package co.prod.service;

import java.util.List;

import co.prod.vo.MemberVO;

public interface MemberService {
	// 회원목록.
	public List<MemberVO> getMembers();
	
	// 회원등록.
	public boolean addMember(MemberVO vo);
	
	// 회원조회.
	public MemberVO getMember(String id);
	
	// 회원수정
	public boolean modifyMember(MemberVO vo);
	
	// 회원삭제
	public boolean removeMember(String id);
	
	// id/pw 로그인 처리.
	public MemberVO login(MemberVO vo);
}	
