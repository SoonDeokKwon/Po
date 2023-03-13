package co.prod.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.common.Control;
import co.prod.service.MemberService;
import co.prod.service.MemberServiceMybatis;

public class MemberRemoveAjax implements Control {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 삭제할 아이디(파라미터 -> 삭체 처리 매개값)
		MemberService service = new MemberServiceMybatis();
		boolean result = service.removeMember(request.getParameter("id"));
		String json = "";
		if(result) { //{"retCode" : "Success"}
			json = "{\"retCode\" : \"Success\"}";
		} else {  //{"retCode" : "Fail"}
			json = "{\"retCode\" : \"Fail\"}";
		}
		return json + ".ajax";
	}

}
