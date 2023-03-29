package co.prod.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.prod.common.Control;
import co.prod.service.ProductService;
import co.prod.service.ProductServiceImpl;
import co.prod.vo.ReplyVO;

public class ReplyModifyAjax implements Control {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		String rid = request.getParameter("replyId");
		String rc = request.getParameter("replyContent");
		
		ReplyVO vo = new ReplyVO();
		vo.setReplyNo(Integer.parseInt(rid));
		vo.setReplyContent(rc);
		
		ProductService service = new ProductServiceImpl();
		
		boolean result = service.updateReply(vo);
		
		vo = service.getReply(Integer.parseInt(rid));
		
		Map<String, Object> resultMap = new HashMap<>();

		if(result) {		
			resultMap.put("retCode", "Success");
			resultMap.put("reply", vo);
		} else {
			resultMap.put("retCode", "Fail");
			resultMap.put("reply", null);
		}
		
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(resultMap);
		
		return json + ".ajax";
	}

}
