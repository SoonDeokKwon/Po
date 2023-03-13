package co.prod.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.prod.common.Control;
import co.prod.service.MemberService;
import co.prod.service.MemberServiceMybatis;
import co.prod.vo.MemberVO;

public class MemberListAjax implements Control {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		MemberService service = new MemberServiceMybatis();
		List<MemberVO> list = service.getMembers();
		
		// {"id":"user1", "passwd":"1234", "name":"홍길동"...} => json
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(list); // json 포맷으로 변경.
		return json + ".ajax";
	}

}