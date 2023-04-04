package co.prod.mapper;

import java.util.List;

import co.prod.vo.EmpVO;
import co.prod.vo.MemberVO;
import co.prod.vo.MembersVO;

public interface MemberMapper {
	// 매퍼(MemberMapper.xml) 에서 실행할 메소드 정의
	public List<MemberVO> getMembers();
	
	// 로그인 용도.
	public MemberVO login(MemberVO vo);
	
	// 회원정보 삭제.
	public int deleteMember(String id);
	
	// 회원등록.
	public int insertMember(MemberVO vo);
	
	// 회원 정보 목록(Jquery)
	public List<MembersVO> memberListJquery();
	// 회원 정보 등록(Jquery)
	public int memberAddJquery(MembersVO vo);
	// 회원 다건 삭제(Jquery).
	public int deleteMembersAry(String[] users);
	
	// 사원 목록.
	public List<EmpVO> selectEmployees();
	// 사원 등록.
	public int insertEmployees(EmpVO vo);
	// 삭제.
	public int deleteEmp(int empId);
	
}
