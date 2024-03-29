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

import co.prod.controller.CalendarAjax;
import co.prod.controller.CalendarDeleteAjax;
import co.prod.controller.CalendarForm;
import co.prod.controller.CalendarInsertAjax;
import co.prod.controller.ChartAjax;
import co.prod.controller.ChartControl;
import co.prod.controller.CovidForm;
import co.prod.controller.DataTableAddAjax;
import co.prod.controller.DataTableAjax;
import co.prod.controller.DataTableForm;
import co.prod.controller.DataTableRemoveAjax;
import co.prod.controller.MapForm;
import co.prod.controller.MemberAddAjax;
import co.prod.controller.MemberAddJquery;
import co.prod.controller.MemberJquery;
import co.prod.controller.MemberListAjax;
import co.prod.controller.MemberListControl;
import co.prod.controller.MemberListJquery;
import co.prod.controller.MemberRemoveAjax;
import co.prod.controller.MemberRemoveJquery;
import co.prod.controller.MembersControl;
import co.prod.controller.ProductInfoControl;
import co.prod.controller.ProductListControl;
import co.prod.controller.ReplyAddAjax;
import co.prod.controller.ReplyListAjax;
import co.prod.controller.ReplyModifyAjax;
import co.prod.controller.ReplyRemoveAjax;
import co.prod.controller.ReplySearchAjax;

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
		// 상품댓글정보 목록 
		map.put("/replyListAjax.do", new ReplyListAjax());
		// 상품 댓글 삭제
		map.put("/replyRemoveAjax.do", new ReplyRemoveAjax());
		// 상품 댓글 등록
		map.put("/replyAddAjax.do", new ReplyAddAjax());
		// 상품 댓글번호 조회.
		map.put("/replySearchAjax.do", new ReplySearchAjax());
		// 상품 댓글 수정.
		map.put("/replyModifyAjax.do", new ReplyModifyAjax());
		
		// chart
		map.put("/chart.do", new ChartControl());
		
		// chart. 데이터
		map.put("/chartAjax.do", new ChartAjax());
		// covid
		map.put("/covid19.do", new CovidForm());
		// mapAPI
		map.put("/map.do", new MapForm());
		// calendarAPI
		map.put("/calendar.do", new CalendarForm());
		// calendarAPIAjax
		map.put("/calendarAjax.do", new CalendarAjax());
		// schedule 입력
		map.put("/calendarInsertAjax.do", new CalendarInsertAjax());
		// schedule 삭제
		map.put("/calendarDeleteAjax.do", new CalendarDeleteAjax());
		
		// jquery용 ajax.
		map.put("/memberJqauery.do", new MemberJquery());
		// 목록을 가지고 오도록. memberListJquery.do
		map.put("/memberListJquery.do", new MemberListJquery());
		// 회원 목록 등록(jquery)
		map.put("/memberAddJquery.do", new MemberAddJquery());
		// 회원 다건 삭제(Jquery).
		map.put("/memberRemoveJquery.do", new MemberRemoveJquery());
		
		// datatable 활용.
		// 사원 목록.
		map.put("/dataTabelForm.do", new DataTableForm());
		map.put("/dataTableAjax.do", new DataTableAjax());
		// 사원 등록.
		map.put("/dataTableAdd.do", new DataTableAddAjax());
		// 삭제.
		map.put("/dataTableRemove.do", new DataTableRemoveAjax());
		
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
