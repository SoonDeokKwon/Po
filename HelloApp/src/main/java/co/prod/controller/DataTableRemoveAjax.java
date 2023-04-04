package co.prod.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.common.Control;
import co.prod.service.MemberService;
import co.prod.service.MemberServiceMybatis;

public class DataTableRemoveAjax implements Control {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		int empId = Integer.parseInt(request.getParameter("employeeId"));
		MemberService service = new MemberServiceMybatis();
		boolean result = service.deleteEmp(empId);
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
