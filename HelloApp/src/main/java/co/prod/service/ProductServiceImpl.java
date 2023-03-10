package co.prod.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.prod.common.DataSource;
import co.prod.mapper.ProductMapper;
import co.prod.vo.ProductVO;

public class ProductServiceImpl implements ProductService {

	SqlSession sqlSession = DataSource.getInstnace().openSession(true);
	ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
	
	@Override
	public List<ProductVO> products() {
		return mapper.productList();
	}
	
	
	
}
