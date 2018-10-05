package tk.mybatis.simple.factory;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MySqlSessionFactory {
    private static SqlSessionFactory sqlSessionFactroy;
    private static SqlSession sqlSession;
    public static SqlSession getSqlSession(){
        if(sqlSession!=null){
            return sqlSession;
        }
        Reader reader =null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactroy =new SqlSessionFactoryBuilder().build(reader);
            sqlSession=sqlSessionFactroy.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sqlSession;
    }
}
