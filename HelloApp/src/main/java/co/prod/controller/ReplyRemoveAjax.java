package co.prod.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.common.Control;
import co.prod.service.ProductService;
import co.prod.service.ProductServiceImpl;

public class ReplyRemoveAjax implements Control {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		String replyId = request.getParameter("replyId");
		ProductService service = new ProductServiceImpl();	
		boolean result = service.removeReply(Integer.parseInt(replyId));
		String json = "";
		
		if(result) {
			//{"retCode": "Success"}
			json = "{\"retCode\": \"Success\"}";
		} else {
			// {"retCode":"Fail"}
			json = "{\"retCode\":\"Fail\"}";
		}
		
		return json + ".ajax";
	}

}
