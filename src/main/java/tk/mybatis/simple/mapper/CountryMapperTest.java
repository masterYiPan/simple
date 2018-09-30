package tk.mybatis.simple.mapper;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import tk.mybatis.simple.model.Country;

import java.io.Reader;
import java.util.List;


public class CountryMapperTest {
	private static SqlSessionFactory sqlSessionFactroy;
	
	public List<Country> find(){
		SqlSession sqlSession = null;
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactroy =new SqlSessionFactoryBuilder().build(reader);
			sqlSession=sqlSessionFactroy.openSession();
			Country country = new Country();
//		    country.setCountryName("越南");country.setCountryCode("CN");
//			sqlSession.insert("Insert", country);
//			sqlSession.commit();
			List<Country> countryList = sqlSession.selectList("SelectAll");
			System.out.println(countryList.toString());
 			reader.close();
			return countryList;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			sqlSession.close();
		}
		return null;
	}
}
