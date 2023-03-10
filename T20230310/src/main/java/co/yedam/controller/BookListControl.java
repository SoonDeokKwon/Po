package co.yedam.controller;

import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.service.BookService;
import co.yedam.service.BookServiceMybatis;
import co.yedam.vo.BookVO;

public class BookListControl implements Command {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookService service = new BookServiceMybatis();
		List<BookVO> list = service.bookList();
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("WEB-INF/book/bookList.jsp").forward(request, response);
		

	}

}
