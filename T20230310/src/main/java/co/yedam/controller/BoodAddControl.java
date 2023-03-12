package co.yedam.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.service.BookService;
import co.yedam.service.BookServiceMybatis;
import co.yedam.vo.BookVO;

public class BoodAddControl implements Command {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String press = request.getParameter("press");
		String desc = request.getParameter("desc");
		int price;
		if(request.getParameter("price")==null) {
			price =0;
		}else {
			price = Integer.parseInt(request.getParameter("price"));
			
		}
			
		
		BookVO vo = new BookVO();
		vo.setBookCode(code);
		vo.setBookTitle(title);
		vo.setBookAuthor(author);
		vo.setBookPress(press);
		vo.setBookDesc(desc);
		vo.setBookPrice(price);
		
		System.out.println("입력:" + vo);
		
		BookService service = new BookServiceMybatis(); 
		boolean result = service.addBook(vo);
		System.out.println(result);
		if(result) {
			request.getRequestDispatcher("/WEB-INF/book/bookList.jsp").forward(request, response);
			
		}
		
	
		
		
		
	}

}
