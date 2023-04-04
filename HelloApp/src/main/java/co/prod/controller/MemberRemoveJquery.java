package co.prod.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.common.Control;
import co.prod.service.MemberService;
import co.prod.service.MemberServiceMybatis;

public class MemberRemoveJquery implements Control {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		String[] members = request.getParameterValues("memberId");
		for (String member : members) {
			System.out.println(member);
		}
		
		MemberService service = new MemberServiceMybatis();
		boolean result = service.removeMembers(members);
		String json ="";
		
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
