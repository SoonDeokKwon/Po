package co.prod.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.prod.common.Control;
import co.prod.service.MemberService;
import co.prod.service.MemberServiceMybatis;
import co.prod.vo.EmpVO;

public class DataTableAddAjax implements Control {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		EmpVO vo = new EmpVO();
		vo.setFirstName(request.getParameter("firstName"));
		vo.setLastName(request.getParameter("lastName"));
		vo.setEmail(request.getParameter("email"));
		vo.setHireDate(request.getParameter("hireDate"));
		vo.setSalary(Integer.parseInt(request.getParameter("salary")));
		
		
		MemberService service = new MemberServiceMybatis();
		boolean result = service.employeeAdd(vo);
		
		Map<String, Object> map = new HashMap<>();
		
		Gson gson = new GsonBuilder().create();
		String json = "";
		
		if(result) {
			
			map.put("retCode", "Success");
			map.put("employee", vo);
		} else {
			map.put("retCode", "Fail");
			map.put("employee", null);
		}
		json = gson.toJson(map);
		
		return json + ".ajax";
	
	}

}
