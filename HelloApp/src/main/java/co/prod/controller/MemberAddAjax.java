package co.prod.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.prod.common.Control;
import co.prod.service.MemberService;
import co.prod.service.MemberServiceMybatis;
import co.prod.vo.MemberVO;

public class MemberAddAjax implements Control {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MemberVO vo = new MemberVO();
		// id, name, pw, mail, auth
		vo.setId(request.getParameter("id"));
		vo.setName(request.getParameter("name"));
		vo.setPasswd(request.getParameter("pw"));
		vo.setMail(request.getParameter("mail"));
		vo.setAuth(request.getParameter("auth"));
		
		MemberService service = new MemberServiceMybatis();
		boolean result =  service.addMember(vo);
		Map<String, Object> map = new HashMap<>();
		
		Gson gson = new GsonBuilder().create();
		String json = "";
		
		if(result) {
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
