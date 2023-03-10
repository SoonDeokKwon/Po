package co.prod.common;

import co.prod.service.ProductService;
import co.prod.service.ProductServiceImpl;

public class TestProd {
	public static void main(String[] args) {
		ProductService service = new ProductServiceImpl();
		service.products().forEach(vo -> System.out.println(vo));
		
	}
}
