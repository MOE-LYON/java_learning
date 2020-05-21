package com.moelyon.learning.mybatis;

import com.moelyon.learning.DataSourceHelper;
import com.moelyon.learning.mybatis.dao.TempMapper;
import com.moelyon.learning.mybatis.entity.TempEntity;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author moelyon
 */
public class Application {

    public static void main(String[] args) {

        var task1 = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getId());
            SqlSession session = DataSourceHelper.getConnection();
            TempMapper tempMapper = session.getMapper(TempMapper.class);
            TempEntity tempEntity2 = tempMapper.findById(1);
            System.out.println(tempEntity2);
        });
        var task2 = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getId());
            SqlSession session = DataSourceHelper.getConnection();
            TempEntity tempEntity1 = session.selectOne("com.moelyon.learning.mybatis.dao.TempMapper.findById",2);
            System.out.println(tempEntity1);
        });
        System.out.println(Thread.currentThread().getId());
        SqlSession msession = DataSourceHelper.getConnection();
        TempMapper mtempMapper = msession.getMapper(TempMapper.class);

        List<TempEntity> entities = mtempMapper.findAll();
        System.out.println(entities);

        try {
            task1.get();
            task2.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
