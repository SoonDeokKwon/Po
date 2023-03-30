package co.prod.mapper;

import java.util.List;
import java.util.Map;

import co.prod.vo.CalendarVO;
import co.prod.vo.ProductVO;
import co.prod.vo.ReplyVO;

public interface ProductMapper {
	// 상품 목록.
	public List<ProductVO> productList();
	
	// 상품 한건 조회.
	public ProductVO productInfo(String code);
	
	// 댓글 목록.
	public List<ReplyVO> replyList(String code);
	// 댓글 삭제.
	public int deleteReply(int replyId);
	// 댓글 등록.
	public int insertReply(ReplyVO vo);
	// 댓글 번호로 조회.
	public ReplyVO selectReply(int rid);
	// 댓글 수정
	public int modifyReply(ReplyVO vo);
	
	// chart. 부서별 인원 현황.
	public List<Map<String, Object>> cahrtInfo();
	
	// calendar schedule
	public List<CalendarVO> calendarInfo();
	// insert schedule
	public int insertSchedule(CalendarVO vo);
	// delete schedule
	public int deleteSchedule(CalendarVO vo);
	
	
}
