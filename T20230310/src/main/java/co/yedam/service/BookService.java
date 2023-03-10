package co.yedam.service;

import java.util.List;

import co.yedam.vo.BookVO;

public interface BookService {
	// 도서목록
	public List<BookVO> bookList();
	// 도서등록
	public boolean addBook(BookVO vo);
	// 도서조회
	public BookVO getBookInfo(String bookCode);
}
