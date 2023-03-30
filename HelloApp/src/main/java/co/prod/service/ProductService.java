package co.prod.service;

import java.util.List;
import java.util.Map;

import co.prod.vo.CalendarVO;
import co.prod.vo.ProductVO;
import co.prod.vo.ReplyVO;

public interface ProductService {
	
	// 목록
	public List<ProductVO> products();
	
	// 상세
	public ProductVO getProduct(String code);
	
	// 상품에 대한 댓글 목록
	public List<ReplyVO> replyList(String code);
	// 상품 댓글 삭제
	public boolean removeReply(int replyId);
	// 댓글 등록
	public boolean addReply(ReplyVO vo);
	//
	public ReplyVO getReply(int rid);
	// 댓글 수정.
	public boolean updateReply(ReplyVO vo);
	
	// chart. 부서별 인원 현황.
	public List<Map<String, Object>> chartInfo();
	
	// calendar 
	public List<CalendarVO> calendarInfo();	
	// insert schedule
	public boolean insertSchedule(CalendarVO vo);	
	// delete schedule
	public boolean deleteSchedule(CalendarVO vo);
	
	
}
