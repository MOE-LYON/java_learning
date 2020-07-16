package com.moelyon.learning.concurrent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class ConnecionPool {

    private LinkedList<Connection> pool = new LinkedList<>();


    public ConnecionPool(int initialSize){
        if(initialSize>0){
            for(int i=0;i<initialSize; ++i){
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    public void releaseConnection(Connection connection){
        if(connection != null){
            synchronized (pool){
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnection(int mills) throws InterruptedException {
        synchronized (pool){
            if(mills<=0){
                while(pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else{
                long future = System.currentTimeMillis()+mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining>0){
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection connection = null;
                if(!pool.isEmpty()){
                    connection = pool.removeFirst();
                }

                return connection;
            }
        }
    }

    public static class ConnectionDriver{
        static class ConnectionHanlder implements InvocationHandler{
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if("commit".equals(method.getName())){
                    TimeUnit.MILLISECONDS.sleep(100);
                }
                return null;
            }
        }

        public static Connection createConnection(){
            return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),new Class[]{Connection.class},new ConnectionHanlder());
        }
    }
}
