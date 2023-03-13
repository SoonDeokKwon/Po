package co.prod.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.controller.MemberAddAjax;
import co.prod.controller.MemberListAjax;
import co.prod.controller.MemberListControl;
import co.prod.controller.MemberRemoveAjax;
import co.prod.controller.MembersControl;
import co.prod.controller.ProductInfoControl;
import co.prod.controller.ProductListControl;

public class FrontController extends HttpServlet{
	
	private Map<String, Control> map;
	public FrontController() {
		map = new HashMap<>();
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		// url <-> control
		map.put("/memberList.do", new MemberListControl());
		map.put("/members.do", new MembersControl());
		// Ajax 호출(SPA처리)
		map.put("/memberListAjax.do", new MemberListAjax());
		map.put("/memberRemoveAjax.do", new MemberRemoveAjax());
		map.put("/memberAddAjax.do", new MemberAddAjax());
		
		// 상품목록.
		map.put("/productList.do", new ProductListControl());
		// 상품한건정보.
		map.put("/productInfo.do", new ProductInfoControl());
		
		
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		System.out.println(uri);
		String context = req.getContextPath();
		System.out.println(context);
		String page = uri.substring(context.length());
		System.out.println(page);
		System.out.println("do page:" + page);
		
		Control command = map.get(page);
		String viewPage = command.exec(req, resp); // product/productList.tile
		
		if(viewPage.endsWith(".jsp")) { // memberList.co(...jsp)
			viewPage = "/WEB-INF/views/" + viewPage;
//		} else if (viewPage.endsWith(".tiles")) { // members.do(...tiles)
			//viewPage = "./" + viewPage;
		} else if (viewPage.endsWith(".ajax")) {
			resp.setContentType("text/json;charset=utf-8");
			resp.getWriter().append(viewPage.substring(0, viewPage.length() - 5));
			return;
		}
		// 페이지 재지정.
		RequestDispatcher rd = req.getRequestDispatcher(viewPage);
		rd.forward(req, resp);
		
		
		
	}
	
	
	
	
}
