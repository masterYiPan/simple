package tk.mybatis.simple.mapper;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import tk.mybatis.simple.factory.MySqlSessionFactory;
import tk.mybatis.simple.model.Country;

import java.io.Reader;
import java.util.List;


public class CountryMapperTest {
	private static SqlSessionFactory sqlSessionFactroy;
	private SqlSession sqlSession=MySqlSessionFactory.getSqlSession();

	public List<Country> find(){
			CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
			List<Country> countryList = countryMapper.SelectAll();
			System.out.println(countryList.toString());
			return countryList;
	}
}
