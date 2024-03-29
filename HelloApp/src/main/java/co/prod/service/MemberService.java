package co.prod.service;

import java.util.List;

import co.prod.vo.EmpVO;
import co.prod.vo.MemberVO;
import co.prod.vo.MembersVO;

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
	
	
	// 회원 목록(Jquery)
	public List<MembersVO> memberListJquery();
	// 회원 등록(Jquery)
	public boolean memberAddJquery(MembersVO vo);
	// 회원 다건 삭제.
	public boolean removeMembers(String[] users);
	
	
	// 사원 목록.
	public List<EmpVO> employeeList();
	// 사원 등록.
	public boolean employeeAdd(EmpVO vo);
	// 삭제.
	public boolean deleteEmp(int empId);
	
	
}	
