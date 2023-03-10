package co.prod.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// ojdbc 활용 : db connection 객체 가져옴.
// 특징: 요청 시마다 객체를 생성하고 요청이 종료되면 반환.

// connection 객체: 서버에 미리 생성 (n개) -> 이 안에서만 사용하게끔(서버 보호를 위함)
// DataSource => Resource 가져오는 룩업;
public class DataSource {
	
	// 싱글톤 방식
	private static SqlSessionFactory sqlSessionFactory;
	private DataSource() {}
	
	public static SqlSessionFactory getInstnace() {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	
	
	
}
