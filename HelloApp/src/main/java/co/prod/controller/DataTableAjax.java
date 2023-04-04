package co.prod.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.common.Control;
import co.prod.service.MemberService;
import co.prod.service.MemberServiceMybatis;
import co.prod.vo.EmpVO;

public class DataTableAjax implements Control {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		//{ "data": [["Tiger Nixon", "System Architect", "$320,800"],
		//           ["Garrett Winter", "Accountant", "$170,750"]]
		//}
		MemberService service = new MemberServiceMybatis();
		List<EmpVO> list = service.employeeList();
		String json = "{\"data\":[";
		for (EmpVO vo : list) {
			json += "[\"" + vo.getEmployeeId() 
						  + "\", \"" + vo.getFirstName() 
						  + "\", \"" + vo.getLastName() 
						  + "\", \"" + vo.getEmail()
						  + "\", \"" + vo.getHireDate()
						  + "\", \"" + vo.getSalary()
					+ "\"],";
		}
		json = json.substring(0, json.length() - 1);
		json += "]}";
		
		return json + ".ajax";
	}

}
