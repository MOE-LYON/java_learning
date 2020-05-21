package com.moelyon.learning;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author moelyon
 */
public class DataSourceHelper {

    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getConnection() {

        if(sqlSessionFactory == null){
            synchronized (DataSourceHelper.class){
                if(sqlSessionFactory == null){
                    init();
                }
            }
        }
        return sqlSessionFactory.openSession();
    }

    private   static  void init()  {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
}
