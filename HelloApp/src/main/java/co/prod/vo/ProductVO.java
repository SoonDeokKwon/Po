package co.prod.vo;

import lombok.Data;

@Data
public class ProductVO {
	
//	PRODUCT_CODE NOT NULL VARCHAR2(10)   
//	PRODUCT_NAME NOT NULL VARCHAR2(100)  
//	ORIGIN_PRICE NOT NULL NUMBER         
//	SALE_PRICE            NUMBER         
//	PRODUCT_DESC NOT NULL VARCHAR2(1000) 
//	LIKE_IT      NOT NULL NUMBER(3,1)    
//	IMAGE                 VARCHAR2(100) 
	
	private String productCode;
	private String productName;
	private int originPrice;
	private int salePrice;
	private String productDesc;
	private double likeIt;
	private String image;
	
}
