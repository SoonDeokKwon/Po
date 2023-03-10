package co.yedam.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.service.BookService;
import co.yedam.service.BookServiceMybatis;
import co.yedam.vo.BookVO;

public class BoodInfoControl implements Command {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookCode = request.getParameter("bookCode");
		
		BookService service = new BookServiceMybatis();
		BookVO vo = service.getBookInfo(bookCode);
		
		request.setAttribute("book", vo);
		
		request.getRequestDispatcher("WEB-INF/book/bookInfo.jsp").forward(request, response);
	}

}
