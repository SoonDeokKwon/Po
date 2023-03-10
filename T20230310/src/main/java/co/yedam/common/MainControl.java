package co.yedam.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainControl implements Command {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 페이지 재지정.
		String path = "WEB-INF/main/main.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

}
