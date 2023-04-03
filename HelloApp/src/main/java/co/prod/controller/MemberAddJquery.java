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
import co.prod.vo.MembersVO;

public class MemberAddJquery implements Control {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		MembersVO vo = new MembersVO();
		vo.setMemberId(request.getParameter("memberId"));
		vo.setMemberName(request.getParameter("memberName"));
		vo.setMemberPw(request.getParameter("memberPw"));
		vo.setMemberAddr(request.getParameter("memberAddr"));
		vo.setMemberTel(request.getParameter("memberTel"));
		
		MemberService service = new MemberServiceMybatis();
		
		
		Gson gson = new GsonBuilder().create();
		String json = "";
		Map<String, Object> map = new HashMap<>();
		
		if(service.memberAddJquery(vo)) {
			map.put("retCode", "Success");
			map.put("member", vo);
		} else {
			map.put("retCode", "Fail");
			map.put("member", null);
		}
		json = gson.toJson(map);
		
		return json + ".ajax";
	}

}
