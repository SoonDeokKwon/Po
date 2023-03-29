package co.prod.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.common.Control;
import co.prod.service.ProductService;
import co.prod.service.ProductServiceImpl;

public class ChartAjax implements Control {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		ProductService service = new ProductServiceImpl();
		List<Map<String, Object>> list = service.chartInfo();
		
		String json = "[";
		for(Map<String, Object> map : list) {
			System.out.println(map.get("departmentName") + "," + map.get("cnt"));
			// {"Administration":1} 형식으로 나오게끔
			json += "{\"" + map.get("departmentName") + "\":" + map.get("cnt") + "}," ;
		}
		json = json.substring(0, json.length() - 1 );
		json += "]";
		
		return json + ".ajax";
	}

}
