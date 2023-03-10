package co.prod.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.common.Control;
import co.prod.service.ProductService;
import co.prod.service.ProductServiceImpl;
import co.prod.vo.ProductVO;

public class ProductListControl implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
		// db에서 결과를 가져오기 -> attribute("list")
		ProductService service = new ProductServiceImpl();
		List<ProductVO> list = service.products();
		
		req.setAttribute("list", list);
		
		return "product/productList.tiles"; // 실행할 페이지.
		
	}

}
